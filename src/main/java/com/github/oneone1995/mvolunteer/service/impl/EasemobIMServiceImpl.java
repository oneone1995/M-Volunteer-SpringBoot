package com.github.oneone1995.mvolunteer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.oneone1995.mvolunteer.config.easemob.EasemobIMProperties;
import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.model.EasemobIMChatGroupModel;
import com.github.oneone1995.mvolunteer.service.CacheService;
import com.github.oneone1995.mvolunteer.service.EasemobIMService;
import com.github.oneone1995.mvolunteer.service.api.EasemobApiService;
import com.github.oneone1995.mvolunteer.web.exception.EasemobGroupCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.GenerateEasemobTokenFailException;
import com.github.oneone1995.mvolunteer.web.exception.PutUserToEasemobGroupFailException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


@Service
@Slf4j
public class EasemobIMServiceImpl implements EasemobIMService {
    @Autowired
    private EasemobIMProperties easemobProperties;

    @Autowired
    private EasemobApiService easemobApiService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String createGroup(EasemobIMChatGroupModel chatGroupModel) throws EasemobGroupCreateFailException {
        log.info("easemob properties: {}", easemobProperties);

        String token = cacheService.getEasemobToken();
        Call<ResponseBody> call = easemobApiService.createChatGroups(
                token,
                easemobProperties.getOrgName(),
                easemobProperties.getAppName(),
                chatGroupModel);

        try {
            Response<ResponseBody> response = call.execute();
            //创建环信群组成功返回群号
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                assert body != null; //目前看起来body是不可能为空的，下面请求失败的情况也是一样
                String bodyString = body.string();
                log.info("create easemob chat-group success: {}", bodyString);

                return getGroupIdFromEasemobResponseBody(bodyString);
            } else {
                //环信群组API请求失败
                ResponseBody errorBody = response.errorBody();

                assert errorBody != null;
                log.error("create easemob chat-group fail: {}", errorBody.string());

                throw new EasemobGroupCreateFailException(ResultStatus.EASEMOB_GROUP_CREATE_FAIL.getMessage());
            }
        } catch (IOException e) {
            log.error("create easemob chat-group fail by IOException : {}", e);
            throw new EasemobGroupCreateFailException(ResultStatus.EASEMOB_GROUP_CREATE_FAIL.getMessage());
        }
    }

    @Override
    public boolean putUser2Group(String groupId, String username) throws PutUserToEasemobGroupFailException {
        log.info("put user to group, username:{}, groupId:{}", username, groupId);

        String token = cacheService.getEasemobToken();
        Call<ResponseBody> call = easemobApiService.putUser2ChatGroup(
                token,
                easemobProperties.getOrgName(),
                easemobProperties.getAppName(),
                groupId,
                username);

        try {
            Response<ResponseBody> response = call.execute();
            if (response.isSuccessful()) {
                //成功将用户添加至群组，返回true标志
                ResponseBody body = response.body();
                assert body != null;
                log.info("put user to chat-group success: {}", body.string());
                return true;
            } else {
                //逻辑错误造成用户进群失败，比如用户已经在群中、群不存在、用户不存在等原因
                ResponseBody errorBody = response.errorBody();
                assert errorBody != null;
                log.error("put user to chat-group fail by {}", errorBody.string());
                throw new PutUserToEasemobGroupFailException(ResultStatus.PUT_USER_TO_EASEMOB_GROUP_FAIL.getMessage());
            }
        } catch (IOException e) {
            //因为网络IO异常造成的失败
            log.error("put user to chat-group fail by IOException : {}", e);
            throw new PutUserToEasemobGroupFailException(ResultStatus.PUT_USER_TO_EASEMOB_GROUP_FAIL.getMessage());
        }
    }

    @Override
    @Cacheable(cacheNames = "easemob-token")
    public String getEasemobToken() {
        //只有redis中缓存不存在时才会调用api发起网络请求生成token
        log.info("get easemob token from api,easemob-properties: {}", easemobProperties);

        Call<ResponseBody> call = easemobApiService.generateToken(
                easemobProperties.getOrgName(),
                easemobProperties.getAppName(),
                easemobProperties.getSecurity());

        try {
            //执行http请求获取数据
            Response<ResponseBody> response = call.execute();
            if (response.isSuccessful()) {
                //成功获得token
                ResponseBody body = response.body();
                assert body != null;

                //body.string()这个方法只能调用一次，因此要先保存
                String bodyString = body.string();
                log.info("generate easemob token success: {}", bodyString);

                //jackson解析json串得到ResponseBody中的access_token, 并加上Bearer
                return "Bearer " + parseAccessTokenFromEasemobResponseBody(bodyString);
            } else {
                //client_id 或 client_secret 错误或者环信服务器内部问题
                ResponseBody errorBody = response.errorBody();
                assert errorBody != null;
                log.error("generate easemob token fail by {}", errorBody.string());
                throw new GenerateEasemobTokenFailException(ResultStatus.GENERATE_EASEMOB_TOKEN_FAIL.getMessage());
            }
        } catch (IOException e) {
            //网络io异常造成的错误
            log.error("generate easemob token fail by IOException : {}", e);
            throw new GenerateEasemobTokenFailException(ResultStatus.GENERATE_EASEMOB_TOKEN_FAIL.getMessage());
        }
    }

    /**
     * 从环信群组服务返回的ResponseBody字符串中解析得到创建成功的群号
     * @param bodyJson 环信群组服务返回的ResponseBody字符串，json格式
     * @return 创建成功的聊天群群号
     * @throws IOException json解析失败时抛出的IOException
     */
    private String getGroupIdFromEasemobResponseBody(String bodyJson) throws IOException {
        EasemobIMChatGroupModel chatGroupModel = objectMapper.readValue(
                objectMapper.readTree(bodyJson).get("data").toString(), EasemobIMChatGroupModel.class);
        log.info("json parsed, return groupId: {}", chatGroupModel.getGroupId());
        return chatGroupModel.getGroupId();
    }

    private String parseAccessTokenFromEasemobResponseBody(String bodyJson) throws IOException {
        String accessToken = objectMapper.readTree(bodyJson).get("access_token").asText();
        log.info("json parsed, return access_token : {}", accessToken);
        return accessToken;
    }
}

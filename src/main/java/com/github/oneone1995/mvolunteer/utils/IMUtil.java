package com.github.oneone1995.mvolunteer.utils;

import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.request.OpenimTribeCreateRequest;
import com.taobao.api.request.OpenimTribeJoinRequest;
import com.taobao.api.request.OpenimTribeQuitRequest;
import com.taobao.api.response.OpenimTribeCreateResponse;
import com.taobao.api.response.OpenimTribeJoinResponse;
import com.taobao.api.response.OpenimTribeQuitResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.github.oneone1995.mvolunteer.config.aliim.IMConstantConfig.*;

/**
 * Created by wangl on 2017/2/26.
 * IM相关的一些工具类
 */
public class IMUtil {

    protected static Logger logger = LoggerFactory.getLogger(IMUtil.class);

    /**
     * 创建普通群组
     * @param currentUser   当前用户，默认为群的管理员
     * @param tribeName     群名
     * @param notice        群公告
     * @return              访问阿里服务器接口的Response
     */
    public static OpenimTribeCreateResponse creteTribe(
            CustomUserDetails currentUser,
            String tribeName,
            String notice
    ) {
        TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECURITY);
        OpenimTribeCreateRequest tribeCreateRequest = new OpenimTribeCreateRequest();
        //=======必要的参数=======//
        //用户信息
        OpenImUser openImUser = new OpenImUser();
        openImUser.setUid(currentUser.getUsername());
        openImUser.setAppKey(APP_KEY);
        openImUser.setTaobaoAccount(false);
        tribeCreateRequest.setUser(openImUser);
        //群名称
        tribeCreateRequest.setTribeName(tribeName);
        //群公告
        tribeCreateRequest.setNotice(notice);
        //群类型
        tribeCreateRequest.setTribeType(0L);
        //添加当前用户为群成员
        List<OpenImUser> openImUserList = new ArrayList<>();
        OpenImUser openImCurrentUser = new OpenImUser();
        openImCurrentUser.setUid(currentUser.getUsername());
        openImCurrentUser.setAppKey(APP_KEY);
        openImCurrentUser.setTaobaoAccount(false);
        openImUserList.add(openImCurrentUser);

        tribeCreateRequest.setMembers(openImUserList);

        OpenimTribeCreateResponse tribeCreateResponse = null;
        try {
            tribeCreateResponse = client.execute(tribeCreateRequest);
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
        }
        return tribeCreateResponse;
    }

    public static OpenimTribeJoinResponse joinTribe(
            CustomUserDetails currentUser,
            Integer tribeId
    ) {
        TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECURITY);
        OpenimTribeJoinRequest tribeJoinRequest = new OpenimTribeJoinRequest();
        //=======必要的参数=======//
        //用户信息
        OpenImUser openImUser = new OpenImUser();
        openImUser.setUid(currentUser.getUsername());
        openImUser.setAppKey(APP_KEY);
        openImUser.setTaobaoAccount(false);
        tribeJoinRequest.setUser(openImUser);
        //群号
        tribeJoinRequest.setTribeId(Long.valueOf(tribeId));

        //发送
        OpenimTribeJoinResponse tribeJoinResponse = null;
        try {
            tribeJoinResponse = client.execute(tribeJoinRequest);
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
        }
        return tribeJoinResponse;
    }


    /**
     * 退出群
     * @param currentUser   当前用户
     * @param tribeId   要退出的群的群号
     * @return  访问阿里API接口的Response
     */
    public static OpenimTribeQuitResponse quitTribe(
            CustomUserDetails currentUser,
            Integer tribeId
    ) {
        TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECURITY);
        OpenimTribeQuitRequest tribeQuitRequest = new OpenimTribeQuitRequest();
        //=======必要的参数=======//
        //用户信息
        OpenImUser openImUser = new OpenImUser();
        openImUser.setUid(currentUser.getUsername());
        openImUser.setAppKey(APP_KEY);
        openImUser.setTaobaoAccount(false);
        tribeQuitRequest.setUser(openImUser);
        //群号
        tribeQuitRequest.setTribeId(Long.valueOf(tribeId));

        //发送
        OpenimTribeQuitResponse tribeQuitResponse = null;
        try {
            tribeQuitResponse = client.execute(tribeQuitRequest);
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
        }
        return tribeQuitResponse;
    }
}

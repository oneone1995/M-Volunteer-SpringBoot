package com.github.oneone1995.mvolunteer.service.api;

import com.github.oneone1995.mvolunteer.model.EasemobIMChatGroupModel;
import com.oneapm.touch.retrofit.boot.annotation.RetrofitService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * 环信即时通讯云API交互Service
 *
 * 注意：我用ResponseBody去接收，也可以建一个与响应结果对应的实体直接映射处理，但是响应失败和成功的返回不一样，需要特殊处理
 * 我是第一次使用retrofit，暂时不知道有什么办法来处理，也许可以通过拦截器来做。因此都统一用ResponseBody来接收响应，然后自己用Jackson来解析想要的结果
 * 参考starter文档和GitHub上的一个项目
 * @see <a href="https://github.com/byWeaponLin/lts-cycle-mission" />
 * @see <a href="https://github.com/syhily/spring-boot-retrofit-support" />
 */
@RetrofitService("easemob")
public interface EasemobApiService {

    /**
     * 通过retrofit请求环信即时通讯云创建群组API
     * @param token 环信token
     * @param orgName 环信企业ID
     * @param appName 环信APP名称
     * @param chatGroupModel 环信群组API交互model
     * @return retrofit包装的请求响应结果
     */
    @POST("{org_name}/{app_name}/chatgroups")
    Call<ResponseBody> createChatGroups(
            @Header("Authorization") String token,
            @Path("org_name") String orgName,
            @Path("app_name") String appName,
            @Body EasemobIMChatGroupModel chatGroupModel);

    /**
     * 通过retrofit请求环信即时通讯云创建群组API
     * @param token 环信token
     * @param orgName 环信企业ID
     * @param appName 环信APP名称
     * @param groupId 环信群组Id
     * @param username 需要加入群组的用户
     * @return retrofit包装的请求响应结果
     */
    @POST("{org_name}/{app_name}/chatgroups/{group_id}/users/{username}")
    Call<ResponseBody> putUser2ChatGroup(
            @Header("Authorization") String token,
            @Path("org_name") String orgName,
            @Path("app_name") String appName,
            @Path("group_id") String groupId,
            @Path("username") String username);
}

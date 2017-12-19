package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.model.EasemobIMChatGroupModel;
import com.github.oneone1995.mvolunteer.service.api.EasemobApiService;
import com.github.oneone1995.mvolunteer.web.exception.EasemobGroupCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.PutUserToEasemobGroupFailException;

/**
 * 环信即时通讯交互的服务接口。
 * 基于下面两个业务需求，需要APP服务端集成环信群组相关接口来和APP业务绑定
 * 1. 志愿组织创建活动之后便创建群组
 * 2. 志愿者报名志愿活动并通过面试后加入对应的群组
 * @see EasemobIMChatGroupModel 与环信API交互实体
 * @see EasemobApiService 环信即时通讯云API交互Service
 */
public interface EasemobIMService {

    /**
     * 创建群组
     * @param chatGroupModel 与环信API交互实体
     * @exception EasemobGroupCreateFailException 环信群组创建失败异常
     * @return 群组创建成功的群号
     */
    String createGroup(EasemobIMChatGroupModel chatGroupModel) throws EasemobGroupCreateFailException;

    /**
     * 将用户添加至环信群组
     * @param groupId 环信IM群组id
     * @param username 需要添加的用户username
     * @return 添加成功与否的标志
     * @throws PutUserToEasemobGroupFailException 添加用户至环信IM聊天群异常
     */
    boolean putUser2Group(String groupId, String username) throws PutUserToEasemobGroupFailException;
}

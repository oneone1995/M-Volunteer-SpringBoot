package com.github.oneone1995.mvolunteer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 环信服务端发送消息的实体
 * 通过服务端创建的群组不会出现在APP群组界面，因此需要服务端在创建群组和有人加群之后主动发送一条消息
 * @see <a href="http://docs.easemob.com/im/100serverintegration/50messages#%E5%8F%91%E9%80%81%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF">
 *     发送消息[环信开发文档]</a>
 * @see com.github.oneone1995.mvolunteer.service.EasemobIMService
 */
@Data
@AllArgsConstructor
@ToString
public class EasemobIMChatMessage {

    // users 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
    @JsonProperty("target_type")
    private String targetType;

    // 注意这里需要用数组，数组长度建议不大于20，即使只有一个用户，
    // 也要用数组 ['u1']，给用户发送时数组元素是用户名，给群组发送时
    // 数组元素是groupid
    private String[] target;

    @JsonProperty("msg")
    private Message message;

    //消息发送者
    private String from;

    @Data
    @ToString
    @AllArgsConstructor
    public static class Message {
        //消息类型,文本消息为text
        private String type;
        //消息内容
        @JsonProperty("msg")
        private String content;
    }
}

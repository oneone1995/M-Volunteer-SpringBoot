package com.github.oneone1995.mvolunteer.domain.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息实体，现在还没真正确定
 */
@Data
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -6955866831134387823L;

    //消息id
    private Long id;

    //消息类型，用来区分群消息和私聊消息
    private Integer type;

    //发送者
    private String sender;

    //消息接收对象，当消息类型为群消息时为对应的群号
    private String receiver;

    //消息
    private String content;

    //消息时间戳
    private Date timestamp;
}

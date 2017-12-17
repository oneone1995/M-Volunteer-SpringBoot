package com.github.oneone1995.mvolunteer.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 环信群组API交互model
 * 这里只做用于创建群组时与环信服务交互的model，原因如下：
 * 这部分环信文档中创建群组和获取群详情中的字段定义不一致，因此暂时不再花时间研究Jackson序列化时有没有好的办法处理
 * 另外文档中创建群组有一个member字段，因为为可选参数且实际业务中在创建群组时也不会用，因此到此处也不再使用。好吧是我太懒了...
 *
 *  @see
 * <a href="http://docs.easemob.com/im/100serverintegration/60groupmgmt#%e7%be%a4%e7%bb%84%e6%95%b0%e6%8d%ae%e7%bb%93%e6%9e%84">
 *     参见群组数据结构[环信开发文档]</a>
 */
@Data
@AllArgsConstructor
public class EasemobIMChatGroupModel implements Serializable {
    private static final long serialVersionUID = -2029531914696293176L;

    //群组ID
    @JsonProperty(value = "groupid")
    private String groupId;

    //群组名称，此属性为必须的
    @JsonProperty(value = "groupname")
    private String groupName;

    //群组描述，此属性为必须的
    @JsonProperty(value = "desc")
    private String description;

    //群组类型：true：公开群，false：私有群。此属性为必须的
    @JsonProperty(value = "public")
    private boolean isPublic;

    //加入群组是否需要群主或者群管理员审批。true：是，false：否。默认是false
    @JsonProperty(value = "members_only", defaultValue = "false")
    private boolean membersOnly;

    //是否允许群成员邀请别人加入此群。 true：允许群成员邀请人加入此群，false：只有群主才可以往群里加人。
    @JsonProperty(value = "allowinvites")
    private boolean allowInvites;

    //群组成员最大数（包括群主），值为数值类型，默认值200，最大值2000，此属性为可选的
    @JsonProperty(value = "maxusers", defaultValue = "200")
    private Integer maxUsers;

    //群组管理员。环信id，此项目中等同于app原有体系的username
    private String owner;

    /*
    *   参考环信创建群组的API，构造下面的json测试可以成功序列化
        {
            "groupname":"为老人开展志愿服务交流群",
            "desc":"3月5日，西湖区紫荆花路9号紫庭南弄紫金庭园芦荻苑9幢3号商铺，为老人开展志愿服务",
            "public":true,
            "maxusers":50,
            "members_only":false,
            "allowinvites": false,
            "owner":"xushaohui"
        }
        使用new ObjectMapper().readValue()方法
        EasemobIMChatGroupModel(
            groupId=null, groupName=为老人开展志愿服务交流群,
            description=3月5日，西湖区紫荆花路9号紫庭南弄紫金庭园芦荻苑9幢3号商铺，为老人开展志愿服务,
            isPublic=true, membersOnly=false, allowInvites=false, maxUsers=50, owner=xushaohui)
    * */
}

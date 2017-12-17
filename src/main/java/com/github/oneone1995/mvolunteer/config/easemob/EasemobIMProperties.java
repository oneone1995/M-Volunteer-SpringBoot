package com.github.oneone1995.mvolunteer.config.easemob;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 环信即时通讯云的配置映射,具体值在环信管理后台可以查到，配置在application.yml中
 * @see
 * <a href="http://docs.easemob.com/im/100serverintegration/10intro#%E7%8E%AF%E4%BF%A1%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%9F%BA%E6%9C%AC%E6%9E%B6%E6%9E%84">
 *     环信服务器基本架构[环信开发文档]</a>
 */

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "easemob")
public class EasemobIMProperties {
    //环信服务体系中企业ID，环信管理后台可以查到
    private String orgName;

    //环信服务体系中的app名字，环信管理后台可以查到
    private String appName;
}

package com.github.oneone1995.mvolunteer.config.retrofit;

import com.oneapm.touch.retrofit.boot.RetrofitServiceScan;
import org.springframework.context.annotation.Configuration;

/**
 * retrofit相关配置类，目前配置都写在了application.yml中
 * 可以参考
 * @see <a href="https://github.com/syhily/spring-boot-retrofit-support"/>
 */
@Configuration
@RetrofitServiceScan("com.github.oneone1995.mvolunteer.service")
public class RetrofitConfig {
}

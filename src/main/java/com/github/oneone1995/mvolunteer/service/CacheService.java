package com.github.oneone1995.mvolunteer.service;

public interface CacheService {
    /**
     * 因为spring AOP导致类内部调用的问题，同一个类中的缓存方法无法调用，所以单独抽了一层接口，其实实际上还是调用的getEasemobToken()
     * 也可以将getEasemobToken()接口从EasemobIMService中去除单独放在一个新的类中，但为了保持功能类似的接口都在一个类中，还是采取了现在的做法
     * todo 会产生循环依赖，但单例的bean和属性注入不会抛出异常，网上有说使用@Postconstruct注解手动注入来解决循环依赖避免异常出现，现在还不会，以后可能会改
     * 参考： {@link EasemobIMService#getEasemobToken()}
     * @see <a href="https://segmentfault.com/q/1010000007005026">spring缓存 同一个 类中调用 缓存无效</a>
     * @see <a href="http://www.jianshu.com/p/9ea7b6bc233e">spring循环依赖</a>
     * @return 环信token
     */
    String getEasemobToken();

    /**
     * 回收活动code随机码，将code码lpush到redis的list
     * @param code 活动结束后待回收的随机码
     */
    void putRandomActivityCode(String code);

    /**
     * 从redis中获取一个随机码，rpop形式
     * @return 用于活动随机码的code值
     */
    String getRandomActivityCode();
}

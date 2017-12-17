package com.github.oneone1995.mvolunteer.service;

/**
 * 即时通讯群组相关业务顶级接口，具体实现取决于你使用的第三方sdk
 * todo 本项目暂时没有使用这个接口，以后可能会使用
 */
public interface IMGroupService<G> {
    /**
     * 创建群组
     * @param group 群组实体，不同sdk有不同的数据结构
     * @param parameter 其它参数，有可能需要，例如之前使用的阿里云旺会有很多参数, 包装成json传入
     * @return 创建群组是否成功的标志
     */
    boolean createGroup(G group, String parameter);
}

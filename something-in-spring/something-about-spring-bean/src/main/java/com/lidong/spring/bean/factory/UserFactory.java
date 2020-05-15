package com.lidong.spring.bean.factory;

import com.lidong.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 *
 * @author Ls J
 * @date 2020/5/16 12:20 AM
 */
public interface UserFactory {

    /**
     * 默认实现 user 创建
     *
     * @return user
     */
    default User createUser() {
        return User.createUser();
    }
}

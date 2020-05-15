package com.lidong.spring.bean.factory;

import com.lidong.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 *
 * @author Ls J
 * @date 2020/5/16 12:17 AM
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}

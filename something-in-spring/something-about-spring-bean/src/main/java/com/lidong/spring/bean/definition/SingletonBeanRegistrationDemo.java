package com.lidong.spring.bean.definition;

import com.lidong.spring.bean.factory.DefaultUserFactory;
import com.lidong.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体 Bean 注册实例
 *
 * @author Ls J
 * @date 2020/5/16 1:33 PM
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 创建一个外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        // 注册外部单例对象
        singletonBeanRegistry.registerSingleton("outerUserFactory", userFactory);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 通过依赖查找的方式来 获取 UserFactory
        UserFactory userFactoryByLookup = applicationContext.getBean("outerUserFactory", UserFactory.class);
        // true
        System.out.println("userFactory  == userFactoryByLookup : " + (userFactory == userFactoryByLookup));

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}

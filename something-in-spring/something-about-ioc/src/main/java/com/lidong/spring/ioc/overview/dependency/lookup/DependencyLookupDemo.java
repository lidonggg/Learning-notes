package com.lidong.spring.ioc.overview.dependency.lookup;

import com.lidong.spring.ioc.overview.annotation.Super;
import com.lidong.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author Ls J
 * @date 2020/5/5 4:07 PM
 * <p>
 * 依赖查找：
 * 1. 通过名称查找
 * 2. 通过类型查找
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 1. 配置 XML 配置文件
        // 2. 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);

        lookupByAnnotationType(beanFactory);
    }

    /**
     * 实时查找
     *
     * @param beanFactory beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    /**
     * 延迟查找
     *
     * @param beanFactory beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("userFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    /**
     * 通过类型查找
     *
     * @param beanFactory beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型查找：" + user);
    }

    /**
     * 按照类型查找集合对象类型
     *
     * @param beanFactory beanFactory
     */
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型查找对象集合：" + users);
        }
    }

    /**
     * 根据 Java 注解查找
     *
     * @param beanFactory beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 所有的 User 集合对象：" + users);
        }
    }
}

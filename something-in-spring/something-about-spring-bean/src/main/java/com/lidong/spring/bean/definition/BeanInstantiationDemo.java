package com.lidong.spring.bean.definition;

import com.lidong.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化
 *
 * @author Ls J
 * @date 2020/5/16 12:09 AM
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("userByStaticMethod", User.class);
        User userByInstanceMethod = beanFactory.getBean("userByInstanceMethod", User.class);
        User userByFactoryBean = beanFactory.getBean("userByFactoryBean", User.class);
        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        // false
        System.out.println(userByStaticMethod == userByInstanceMethod);
        // false
        System.out.println(userByStaticMethod == userByFactoryBean);
        // false
        System.out.println(userByInstanceMethod == userByFactoryBean);
    }
}

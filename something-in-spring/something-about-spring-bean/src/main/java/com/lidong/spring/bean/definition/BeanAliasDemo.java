package com.lidong.spring.bean.definition;

import com.lidong.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名
 *
 * @author Ls J
 * @date 2020/5/11 9:34 PM
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        // 通过原名和别名分别去获取 Bean
        User user = beanFactory.getBean("user", User.class);
        User userAlias = beanFactory.getBean("user-alias", User.class);
        // 是同一个 Bean
        System.out.println(user == userAlias);
    }
}

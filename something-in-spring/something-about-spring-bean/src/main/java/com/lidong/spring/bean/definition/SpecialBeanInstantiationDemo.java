package com.lidong.spring.bean.definition;

import com.lidong.spring.bean.factory.DefaultUserFactory;
import com.lidong.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * Bean 实例化的特殊方法
 *
 * @author Ls J
 * @date 2020/5/16 12:39 AM
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        // Service loader 方法一：通过 Bean 去获取
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        showServiceLoader(serviceLoader);

//        demoServiceLoader();

        // 创建 UserFactory 对象，通过 AutowireCapableBeanFactory
        // 要用实现类 DefaultUserFactory，不能用接口类 UserFactory
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static void demoServiceLoader() {
        // Service loader 方法二
        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        showServiceLoader(serviceLoader);
    }

    private static void showServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        for (UserFactory userFactory : serviceLoader) {
            System.out.println(userFactory.createUser());
        }
    }
}

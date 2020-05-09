## Spring IoC 容器概述

Spring 框架实现了依赖反转的原则，IoC 也可以被称为依赖注入（DI），DI 是 IoC 实现的一种方式，我们可用通过构造器参数、工厂方法或者属性的 setter 方法来注入一些其他的对象来完成依赖的注入，容器会把这些依赖注入的信息放到它创建的 Bean 中去。

所以，Spring 框架是 IoC 的一个实现，DI 是实现的原则。

### [Spring IoC 依赖查找](https://github.com/lidonggg/Learning-notes/blob/master/something-in-spring/something-about-ioc/src/main/java/com/lidong/spring/ioc/overview/dependency/lookup/DependencyLookupDemo.java)

- 根据 Bean 名称查找
    - 实时查找
    - 延迟查找
- 根据 Bean 类型查找
    - 单个 Bean 对象
    - 集合 Bean 对象
- 根据 Bean 名称+类型查找
- 根据 Java 注解查找
    - 单个 Bean 对象
    - 集合 Bean 对象

### [Spring IOC 依赖注入](https://github.com/lidonggg/Learning-notes/blob/master/something-in-spring/something-about-ioc/src/main/java/com/lidong/spring/ioc/overview/dependency/injection/DependencyInjectionDemo.java)

- 根据 Bean 名称注入
- 根据 Bean 类型注入
    - 单个 Bean 对象
    - 集合 Bean 对象
- 注入容器内建 Bean 对象
- 注入非 Bean 对象
- 注入类型
    - 实时注入
    - 延迟注入

### Spring IoC 依赖来源

- 自定义 Bean
- 容器内建 Bean 对象
- 容器内建依赖

### Spring IoC 配置元信息

- Bean 定义配置
    - 基于 XML 文件
    - 基于 Properties 文件
    - 基于 Java 注解
    - 基于 Java api
- IoC 容器配置
    - 基于 XML 文件
    - 基于 Java 注解
    - 基于 Java api
- 外部化属性配置
    - 基于 Java 注解

### Spring IoC 容器

> BeanFactory 和 ApplicationContext 谁才是 Spring IoC 容器？

ApplicationContext 就是 BeanFactory，前者是后者的一个子接口。ApplicationContext 是 BeanFactory 的超集，它增加了一些特性：

- 面向切面（AOP）
- 配置元信息
- 资源管理
- 事件发布
- 国际化
- 注解
- Enviroment 抽象
- 提供了一些应用级别的上下文，比如 web 应用中的 WebApplicationContext

BeanFactory 是一个底层的 IoC 容器，ApplicationContext 在这基础上增加了一些特性。ApplicationContext 组合了 BeanFactory 的实现（通过 ApplicationContext 的 getBeanFactory() 方法可用获取到这个 BeanFactory 的实现），有点类似于 ApplicationContext 代理了 BeanFactory。

#### [使用 BeanFactory 和 ApplicationContext](https://github.com/lidonggg/Learning-notes/blob/master/something-in-spring/something-about-ioc/src/main/java/com/lidong/spring/ioc/overview/container)

#### Spring IoC 容器生命周期

- 启动
- 运行
- 关闭



### 面试题

> 1.BeanFactory 和 FactoryBean 的区别

解答：
BeanFactory 是 IoC 的底层容器；FactoryBean 是创建 Bean 的一种方式，帮助实现复杂的初始化逻辑。

> 2.Spring IoC 启动的时候做了哪些事情

解答：

- IoC 配置源信息的读取和解析（XML、@Bean 注解等方式）
- IoC 容器生命周期管理
- Spring 事件发布
- 国际化
- 。。。
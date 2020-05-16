## Spring Bean

### <a id='定义 Spring Bean'>定义 Spring Bean</a>

什么是 BeanDefinition？

BeanDefinition 是 Spring Framework 定义 Bean 的配置元信息接口，包含：

- Bean 的类名
- Bean 行为配置元素，如作用域、自动绑定的模式、生命周期回调等
- 其他 Bean 引用，又可称为合作者（Collaborators）或者依赖（Dependencies）
- 配置设置，如 Bean 属性（properties）



### [<a id='BeanDefinition 元信息'>BeanDefinition 元信息</a>](https://github.com/lidonggg/Learning-notes/blob/master/something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanDefinitionCreatorDemo.java)

|           属性           |                      说明                       |
| :----------------------: | :---------------------------------------------: |
|          Class           |  Bean 全类名，必须是具体类，不能用抽象类或接口  |
|           Name           |               Bean 的名称或者 ID                |
|          Scope           |    Bean 的作用域(如:singleton、prototype 等)    |
|  Constructor arguments   |          Bean 构造器参数(用于依赖注入)          |
|        Properties        |           Bean 属性设置(用于依赖注入)           |
|     Autowiring mode      |      Bean 自动绑定模式(如:通过名称 byName)      |
| Lazy initialization mode | Bean 延迟初始化模式(延迟和非延迟)，默认为非延迟 |
|  Initialization method   |             Bean 初始化回调方法名称             |
|    Destruction method    |              Bean 销毁回调方法名称              |

BeanDefinition 构建方式：

- 通过 BeanDefinitionBuilder

  ```java
  // 1.通过 BeanDefinitionBuilder 构建
  BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
  // 通过属性设置
  beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "dongdong");
  // 获取 BeanDefinition 实例
  BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
  ```

- 通过 AbstractBeanDefinition 以及派生类

  ```java
  // 2. 通过 AbstractBeanDefinition 以及派生类
  GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
  // 设置 Bean 类型
  genericBeanDefinition.setBeanClass(User.class);
  // 通过 MutablePropertyValues 批量操作属性
  MutablePropertyValues propertyValues = new MutablePropertyValues();
  // propertyValues.addPropertyValue("id", 1);
  // propertyValues.addPropertyValue("name", "小马哥");
  propertyValues.add("id", 1).add("name", "小马哥");
  // 通过 set MutablePropertyValues 批量操作属性
  genericBeanDefinition.setPropertyValues(propertyValues);
  ```

  ​

### 命名 Spring Bean

Bean 的名称：

- 每个 Bean 拥有一个或多个标识符(identifiers)，这些标识符在 Bean 所在的容器必须是唯一的。通常，一个 Bean 仅有一个标识符，如果需要额外的，可考虑使用别名(Alias)来扩充。
- 在基于 XML 的配置元信息中，开发人员可用 id 或者 name 属性（XML 标签的属性）来规定 Bean 的 标识符。通常Bean 的 标识符由字母组成，允许出现特殊字符。如果要想引入 Bean 的别名的话，可在 name 属性使用半角逗号（“,”）或分号（“;”） 来间隔。
- Bean 的 id 或 name 属性并非必须制定，如果留空的话，容器会为 Bean 自动生成一个唯一的名称。Bean 的命名尽管没有限制，不过官方建议采用驼峰的方式，更符合 Java 的命名约定。

Bean 名称生成器(BeanNameGenerator)，由 Spring Framework 2.0.3 引入，框架內建两种实现:

- DefaultBeanNameGenerator:默认通用 BeanNameGenerator 实现。
- AnnotationBeanNameGenerator:基于注解扫描的 BeanNameGenerator 实现，起始于 Spring Framework 2.5。



### [Spring Bean 别名](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanAliasDemo.java)

别名有什么作用：

- 复用现有的 BeanDefinition

- 更具有场景化的命名方式，可以根据不同的场景进行区分，比如：

  ```xml
  <alias name="myApp-dataSource" alias="subsystemA-dataSource"/> 
  <alias name="myApp-dataSource" alias="subsystemB-dataSource"/>
  ```

- 通过别名获取到的 Bean 与通过原名获取的 Bean 是同一个 Bean：

  ```java
  // 通过原名和别名分别去获取 Bean
  User user = beanFactory.getBean("user", User.class);
  User userAlias = beanFactory.getBean("user-alias", User.class);
  // 是同一个 Bean，输出 true
  System.out.println(user == userAlias);
  ```



### [<a id="注册 Spring Bean">注册 Spring Bean</a>](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/AnnotationBeanDefinitionDemo.java)

BeanDefinition 注册：

- XML 配置元信息：

  ```xml
  <bean name=”...” ... />
  ```

- Java 注解配置元信息

  ```java
  @Bean
  @Component
  @Import
  ```

- Java API 配置元信息

  ```java
  BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
  // 命名方式
  // eanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)
  registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
  // 非命名方式：
  BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
  // 配置类方式（AnnotatedBeanDefinitionReader#register(Class...)）
  AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
  applicationContext.register(AnnotationBeanDefinitionDemo.class);
  ```

- [注册外部单例对象](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/SingletonBeanRegistrationDemo.java)

  ```java
  // 创建一个外部 UserFactory 对象
  UserFactory outerUserFactory = new DefaultUserFactory();
  SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
  // 注册外部单例对象
  singletonBeanRegistry.registerSingleton("outerUserFactory", outerUserFactory);
  // 通过依赖查找的方式来 获取 UserFactory
  UserFactory userFactoryByLookup = applicationContext.getBean("outerUserFactory", UserFactory.class);
  // true
  System.out.println("outerUserFactory  == userFactoryByLookup : " + (outerUserFactory == userFactoryByLookup));
  ```

  ​

### 实例化 Spring Bean

- [常规方式](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanInstantiationDemo.java)：

  - 通过构造器(配置元信息:XML、Java 注解和 Java API )
  - 通过静态工厂方法(配置元信息:XML 和 Java API )

  ```java
  public static User createUser() {
      User user = new User();
      user.setId(1L);
      user.setName("dlif");
      return user;
  }

  // XML
  <bean id="user-by-static-method" class="com.lidong.spring.ioc.overview.domain.User" factory-method="createUser"/>
  ```

  - 通过 Bean 工厂方法(配置元信息:XML和 Java API )

  ```java
  public User createUser() {
      return User.createUser();
  }

  // XML
  <bean id="user-by-instance-method" class="com.lidong.spring.bean.factory.DefaultUserFactory" factory-method ="createUser"/>

  ```

  - 通过 FactoryBean(配置元信息:XML、Java 注解和 Java API )

  ```java
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

  // XML
  <bean id="user-by-factory-bean" class="com.lidong.spring.bean.factory.UserFactoryBean" />
  ```

- [特殊方式](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/SpecialBeanInstantiationDemo.java)：

  - 通过 ServiceLoaderFactoryBean (配置元信息:XML、Java 注解和 Java API )

    它用来定位类路径上的某个接口的实现类。这种方式能够让我们在 Java 运行时动态发现类路径 classpath 上指定接口的某个实现，是一种动态工厂加载模式，这样就分离了 API 模块和它的多个具体实现模块类。Service Loader 有一个约束，需要Jar包里存在一个目录 META-INF/services 目录，在这个目录里有一个文件，文件的名称完全是接口的完整路径名称，而其内容则是指定接口的实现类完整路径名称。

  ```java
  // 需要有 /META-INF/services/
  public static void demoServiceLoader() {
      ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
      displayServiceLoader(serviceLoader);
  }
  ```

  - 通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)

  ```java
  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
  // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
  AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
  // 创建 UserFactory 对象，通过 AutowireCapableBeanFactory
  UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
  ```

  - 通过 BeanDefinitionRegistry#registerBeanDefinition(String,BeanDefinition)

  ```java
  // 创建 BeanFactory 容器
  AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
  // 注册 Configuration Class（配置类）
  applicationContext.register(AnnotationBeanDefinitionDemo.class);
  // 构造 Bean
  BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
  beanDefinitionBuilder
                  .addPropertyValue("id", 1L)
                  .addPropertyValue("name", "lidong");
  // 注册
  applicationContext.registerBeanDefinition("user-dlif", beanDefinitionBuilder.getBeanDefinition());
  ```

  ​

### [<a id='初始化 Spring Bean'>初始化 Spring Bean</a>](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanInitializationDemo.java)

- @PostConstruct 注解
- 实现 InitializingBean 接口的 afterPropertiesSet() 方法
- 自定义初始化方法：
  - XML 配置:<bean init-method=”init” ... />
  - Java 注解:@Bean(initMethod=”init”)
  - Java API:AbstractBeanDefinition#setInitMethodName(String)

以上几种方法的执行顺序是：

@PostConstruct : UserFactory  -> InitializingBean#afterPropertiesSet()  -> 自定义初始化方法



### [延迟初始化 Spring Bean](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanInitializationDemo.java)

- XML：<bean lazy-init="true" … />
- Java 注解：@Lazy

非延迟初始化与延迟初始化的区别（区别主要在于应用上下文初始化完成之前还是之后执行初始化操作）：

- 非延迟初始化在 Spring 应用上下文启动完成的石斛被初始化，主要体现在 ``applicationContext.refresh();`` 方法中会去直接初始化所有 non-lazy-init（非延迟初始化）实例；
- 延迟初始化则是一种按需初始化。



### [销毁 Spring Bean](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanInitializationDemo.java)

- @PreDestroy 注解
- 实现 DisposableBean 接口的 destroy() 方法
- 自定义销毁方法
  - XML 配置:<bean destroy="destroy" ... />
  - Java 注解:@Bean(destroy="destroy")
  - Java API:AbstractBeanDefinition#setDestroyMethodName(String)

Bean 的销毁是在 ``applicationContext.close();`` 中完成的。

 以上几种方法的执行顺序是：

@PreDestroy -> DisposableBean#destroy() -> 自定义销毁方法

执行顺序与 <a href ='#始化 Spring Bean'>初始化 Bean</a> 类似



### [<a id='垃圾回收 Spring Bean'>垃圾回收 Spring Bean</a>](../../something-in-spring/something-about-spring-bean/src/main/java/com/lidong/spring/bean/definition/BeanGarbageCollectionDemo.java)

- 关闭 Spring 容器（应用上下文）

  ```java
  applicationContext.close();
  ```

- 执行 GC

  ```java
  System.gc();
  ```

- Spring Bean 覆盖的 finalize() 方法回调

  ```java
  @Override
  public void finalize() throws Throwable {
      System.out.println("当前 DefaultUserFactory 对象正在被垃圾回收...");
  }
  ```

  ​

### 面试

- 如何注册一个 Spring Bean？

  可以通过 BeanDefinition 和外部单体对象来注册。

- 什么是 Spring BeanDefinition？

  参考 <a href='#定义 Spring Bean'>定义 Spring Bean</a>和 <a href='#BeanDefinition 元信息'>BeanDefinition 元信息</a>。

- Spring 容器怎样管理注册 Bean？

  IoC 配置元信息读取和解析、依赖查找和注入以及 Bean 生命周期等。
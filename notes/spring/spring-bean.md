## Spring Bean

### 定义 Spring Bean

什么是 BeanDefinition？

BeanDefinition 是 Spring Framework 定义 Bean 的配置元信息接口，包含：

- Bean 的类名
- Bean 行为配置元素，如作用域、自动绑定的模式、生命周期回调等
- 其他 Bean 引用，又可称为合作者（Collaborators）或者依赖（Dependencies）
- 配置设置，如 Bean 属性（properties）

### BeanDefinition 元信息

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



### Spring Bean 别名



### 注册 Spring Bean



### 实例化 Spring Bean



### 初始化 Spring Bean



### 延迟初始化 Spring Bean



### 销毁 Spring Bean



### 垃圾回收 Spring Bean



### 面试
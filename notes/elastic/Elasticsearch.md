# Elasticsearch

目标：从2019年7月份开始，一年内考取Elastic认证。



## 主要功能

- 海量数据的分布式存储以及集群管理
  - 服务与数据的高可用
  - 水平扩展
- 近实时搜索，性能卓越
  - 结构化/全文/地理位置/自动完成
- 海量数据的近实时分析
  - 聚合功能

## Elastic Stack生态圈

### Logstash 数据处理管道

> Logstash 是开源的服务端数据处理管道，支持从不同的来源采集数据、转换数据，并将数据发送到不同的存储库中，2013 年被 Elasticsearch 公司收购。

#### Logstash 特性

- 实时解析和转换数据
  - 从 ip 地址破译出地理坐标
  - 将 PII 数据匿名化，完全排除敏感字段
- 可扩展
  - 200多个插件（日志/数据库/Arcsigh/Netflow）
- 可靠性安全性
  - Logstash会通过持久化队列来保证至少将运行中的事件送达一次
  - 数据加密传输
- 监控

### Kibana 可视化分析利器

- Kibana 名字的含义 = Kiwifruit + Banana
- Kibana 是数据可视化工具，帮助用户解开对数据的任何疑问
- 基于 Logstash 工具，2013年加入Elastic公司

### Beats轻量的数据采集器（基于golang开发）

### X-Pack商业化套件

## Elasticsearch 安装与运行

### 安装运行

- https://www.elastic.co/cn/downloads/elasticsearch 选择对应的版本下载二进制文件

- 运行 

  ```Shell
  $ cd elasticsearch/bin
  $ elasticsearch
  ```

  - 运行报错1：``org.elasticsearch.bootstrap.StartupException: java.lang.RuntimeException: can not run elasticsearch as root``:

    - 解决：elesticsearch 不允许使用root身份运行，需要更改文件夹的所有者然后使用普通用户身份运行。

  - 报错2：``max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]``

    - 解决：

      ```Shell
      $ vim /etc/sysctl.conf
      $ 添加 vm.max_map_count = 262144 保存
      ```

  - 报错3：``the default discovery settings are unsuitable for production use; at least one of [discovery.seed_hosts, discovery.seed_providers, cluster.initial_master_nodes] must be configured``

    - 解决：

      ```Shell
      $ vim config/elasticsearch.yml
      $ 取消注释 node.name: node-1
      $ 取消注释 cluster.initial_master_nodes: ["node-1", "node-2"]
      ```

- 安装插件

  ```Shell
  $ ./elasticsearch-plugin list   # 列出所有已安装的插件
  $ ./elasticsearch-plugin install [插件名称]  # 安装插件
  ```

  - 通过URL访问插件：

    ```Shell
    http://host:9200/_cat/plugins
    ```

- 运行多个实例

  ```Shell
  $ ./elasticsearch -E node.name=node1 -E cluster.name=dlif -E path.data=node1_data -d
  # 其中 node.name 代表节点名称
  # cluster.name 代表集群名称
  # path.data 代表数据路径

  $ ps | grep elasticsearch
  $ kill pid.  #删除进程
  ```

  ​

### Elasticsearch 的文件目录结构

| 目录    | 配置文件          | 描述                                                     |
| ------- | :---------------- | :------------------------------------------------------- |
| bin     |                   | 脚本文件，包括启动 elasticsearch，安装插件，运行统计数据 |
| config  | elasticsearch.yml | 集群配置文件，user、role based相关配置                   |
| JDK     |                   | Java运行环境                                             |
| data    | path.data         | 数据文件                                                 |
| lib     |                   | Java类库                                                 |
| logs    | path.log          | 日志文件                                                 |
| modules |                   | 包含所有 ES 模块                                         |
| plugins |                   | 包含所有已安装插件                                       |

#### JVM配置

1. 修改JVM — config/jvm.options
   - 7.2 下载的默认配置是1GB
2. 生产环境配置的建议
   - Xmx 和 Xms 设置成一样
   - Xmx 不要超过机器内存的 50%
   - 不要超过 30GB - https://www.elastic.co/blog/a-heap-of-trouble
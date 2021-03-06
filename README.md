
[spring-boot-starter-dubbo demo](https://github.com/teaey/spring-boot-starter-dubbo)

prerequisite:   
run zookeeper-3.4.9
use default port 2181


[监控与管理dubbo服务](http://qinghua.github.io/dubbo-3/)       
[dubbokeeper是一个开源版本基于spring mvc开发的社区版dubboadmin](https://github.com/dubboclub/dubbokeeper)     

git clone https://github.com/dubboclub/dubbokeeper.git   
cd dubbokeeper   
mvn clean install -Dmaven.test.skip    
copy  dubbokeeper-ui/target/dubbokeeper-ui-1.0.1.war  to apache-tomcat-9.0.0.M21\webapps(deploy to tomcat)      
   
start zookeeper with default port(2181)   
start mysql :jdbc:mysql://localhost:3306    
```sql
CREATE DATABASE `dubbokeeper`  DEFAULT CHARACTER SET utf8mb4;  
DROP TABLE IF EXISTS `application`;  
CREATE TABLE `application` (  
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `name` varchar(100) NOT NULL DEFAULT '',
  `type` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
start tomcat

```text
分包
建议将服务接口，服务模型，服务异常等均放在API包中，因为服务模型及异常也是API的一部分，
同时，这样做也符合分包原则：重用发布等价原则(REP)，共同重用原则(CRP)
如果需要，也可以考虑在API包中放置一份spring的引用配置，这样使用方，只需在Spring加载过程中引用此配置即可，
配置建议放在模块的包目录下，以免冲突，如：com/alibaba/china/xxx/dubbo-reference.xml
粒度
服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否则将面临分布式事务问题，Dubbo暂未提供分布式事务支持。
服务接口建议以业务场景为单位划分，并对相近业务做抽象，防止接口数量爆炸
不建议使用过于抽象的通用接口，如：Map query(Map)，这样的接口没有明确语义，会给后期维护带来不便。
版本
每个接口都应定义版本号，为后续不兼容升级提供可能，如：<dubbo:service interface="com.xxx.XxxService" version="1.0" />
建议使用两位版本号，因为第三位版本号通常表示兼容升级，只有不兼容时才需要变更服务版本。
当不兼容时，先升级一半提供者为新版本，再将消费者全部升为新版本，然后将剩下的一半提供者升为新版本。
兼容性
服务接口增加方法，或服务模型增加字段，可向后兼容，删除方法或删除字段，将不兼容，枚举类型新增字段也不兼容，需通过变更版本号升级。
各协议的兼容性不同，参见： 服务协议
枚举值
如果是完备集，可以用Enum，比如：ENABLE, DISABLE。
如果是业务种类，以后明显会有类型增加，不建议用Enum，可以用String代替。
如果是在返回值中用了Enum，并新增了Enum值，建议先升级服务消费方，这样服务提供方不会返回新值。
如果是在传入参数中用了Enum，并新增了Enum值，建议先升级服务提供方，这样服务消费方不会传入新值。
序列化
服务参数及返回值建议使用POJO对象，即通过set,get方法表示属性的对象。
服务参数及返回值不建议使用接口，因为数据模型抽象的意义不大，并且序列化需要接口实现类的元信息，并不能起到隐藏实现的意图。
服务参数及返回值都必需是byValue的，而不能是byRef的，消费方和提供方的参数或返回值引用并不是同一个，只是值相同，Dubbo不支持引用远程对象。
异常
建议使用异常汇报错误，而不是返回错误码，异常信息能携带更多信息，以及语义更友好，
如果担心性能问题，在必要时，可以通过override掉异常类的fillInStackTrace()方法为空方法，使其不拷贝栈信息，
查询方法不建议抛出checked异常，否则调用方在查询时将过多的try...catch，并且不能进行有效处理，
服务提供方不应将DAO或SQL等异常抛给消费方，应在服务实现中对消费方不关心的异常进行包装，否则可能出现消费方无法反序列化相应异常。
调用
不要只是因为是Dubbo调用，而把调用Try-Catch起来。Try-Catch应该加上合适的回滚边界上。
对于输入参数的校验逻辑在Provider端要有。如有性能上的考虑，服务实现者可以考虑在API包上加上服务Stub类来完成检验。
```

[用户指南](http://dubbo.io/User+Guide-zh.htm#UserGuide-zh-%E5%85%A5%E9%97%A8)


### 帮助文档

>1.这是一个starter服务，拉取代码，在相应的服务的pom.xml中配置依赖
>
>```xml
>        <dependency>
>            <groupId>com.ichong</groupId>
>            <artifactId>starter-ichong-jobhander</artifactId>
>            <version>0.0.1-SNAPSHOT</version>
>        </dependency>
>```
>
>2.配置starter服务需要的配置信息
>
>```yml
>#redission的配置信息
>singleServerConfig:
>  idleConnectionTimeout: 10000
>  connectTimeout: 10000
>  timeout: 3000
>  retryAttempts: 3
>  retryInterval: 1500
>  # 如果Redis服务端配置有密码需要替换password的值
>  password: null
>  subscriptionsPerConnection: 5
>  clientName: null
>  # 替换为自己真实Redis服务端连接
>  address: "redis://127.0.0.1:6379"
>  subscriptionConnectionMinimumIdleSize: 1
>  subscriptionConnectionPoolSize: 50
>  connectionMinimumIdleSize: 24
>  connectionPoolSize: 64
>  database: 0
>  dnsMonitoringInterval: 5000
>threads: 16
>nettyThreads: 32
>codec: "!<org.redisson.codec.MarshallingCodec> {}"
>transportMode: "NIO"
>
>xxl-job:
>  #xxl-job管理平台的账号
>  username: admin
>  #xxl-job管理平台的密码
>  password: 123456
>  #xxl-job管理平台(改成你公司的对应地址)
>  url: http://127.0.0.1:8080/xxl-job-admin
>```
>
>3.注意依赖冲突，starter服务依赖了redission/xxl-job-core/hutool-all
>
>```xml
>		<!--相关的依赖服务可以去除xxl-job-core的配置-->
>		<dependency>
>			<groupId>com.xuxueli</groupId>
>			<artifactId>xxl-job-core</artifactId>
>			<version>2.4.1-SNAPSHOT</version>
>		</dependency>
>
>		<dependency>
>			<groupId>org.springframework.boot</groupId>
>			<artifactId>spring-boot-autoconfigure</artifactId>
>		</dependency>
>
>		<dependency>
>			<groupId>cn.hutool</groupId>
>			<artifactId>hutool-all</artifactId>
>			<version>5.8.15</version>
>		</dependency>
>
>		<!-- https://mvnrepository.com/artifact/org.redisson/redisson-spring-boot-starter -->
>		<dependency>
>			<groupId>org.redisson</groupId>
>			<artifactId>redisson-spring-boot-starter</artifactId>
>			<version>3.20.0</version>
>		</dependency>
>```
>
>
>
>
>
>
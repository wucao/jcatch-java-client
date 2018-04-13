## Building with Maven
``` xml
<repositories>
    <repository>
        <id>xxg-repository</id>
        <url>http://repo.maven.xxgblog.com/</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.xxg.jcatch</groupId>
        <artifactId>jcatch-client</artifactId>
        <version>1.0.8</version>
    </dependency>
</dependencies>
```
## Java client
``` java
try {
    ....
} catch (Exception e) {
    JCatchClient jCatchClient = new JCatchClient("[your host]", "[your appId]", "[your secretKey]");
    jCatchClient.submit(e);
}
```
## Running with Spring MVC
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="5" />
        <property name="maxPoolSize" value="10" />
        <property name="queueCapacity" value="25" />
    </bean>
    <bean id="springAsycJCatchClient" class="com.xxg.jcatch.client.SpringAsycJCatchClient">
        <property name="appId" value="[your appId]" />
        <property name="baseUrl" value="[your host]" />
        <property name="secretKey" value="[your secretKey]" />
        <property name="executor" ref="taskExecutor" />
    </bean>

    <!-- implements org.springframework.web.servlet.HandlerExceptionResolver -->
    <bean class="com.xxg.jcatch.client.JCatchSpringExceptionResolver">
        <property name="jCatchClient" ref="springAsycJCatchClient" />
    </bean>
</beans>
```

## JCatchSpringExceptionResolver exclude Exceptions
``` xml
<bean class="com.xxg.jcatch.client.JCatchSpringExceptionResolver">
    <property name="jCatchClient" ref="springAsycJCatchClient" />
    <property name="excludedExceptionClasses">
        <array>
            <value>org.springframework.security.access.AccessDeniedException</value>
            <value>org.eclipse.jetty.io.EofException</value>
        </array>
    </property>
</bean>
```
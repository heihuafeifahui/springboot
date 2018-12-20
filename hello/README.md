# Hellow World

##文件目录
![](https://ws1.sinaimg.cn/large/007jVdWKgy1fyd9upxc0pj30er0l843i.jpg)

* src/main/java  程序开发以及主程序入口

* src/main/resources 配置文件

* src/test/java  测试程序



##自动生成的pom（springboot2.1.1版本）
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.test</groupId>
    <artifactId>hello</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>hello</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--核心模块，包括自动配置支持、日志和YAML；-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <!--测试模块，包括JUnit、Hamcrest、Mockito。-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <!--为Spring Boot应用提供了执行Maven操作的可能。-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```


##自动生成的主程序
```java
package com.test.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 来标注一个主程序类，说明这是一个Spring
 */
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        //Spring应用启动
        SpringApplication.run(HelloApplication.class, args);
    }

}
```
###pom.xml中添加支持web的模块： 
```
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
```
##创建控制器
```
package com.test.hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qy
 * @Description: ${todo}
 * @date 2018/12/20
 */
@Controller
public class HelloController {

    /**
     * @ResponseBody 将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML
     * @GetMapping Get的方式请求http映射
     */
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}


```
>访问 http://localhost:8080/hello
![](https://ws1.sinaimg.cn/large/007jVdWKgy1fyda8l2lczj30go06e3yj.jpg)

###打开的src/test/下的测试入口，编写简单的http请求来测试；使用mockmvc进行，利用MockMvcResultHandlers.print()打印出执行结果。

```java
package com.test.hello;

import com.test.hello.web.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith 表示使用SpringTest组件进行单元测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationTests {

    /**
     * 测试逻辑
     * 1.MockMvcBuilder构造MockMvc的构造器
     * 2.mockMvc调用perform，执行一个RequestBuilder请求，调用controller的业务处理逻辑
     * 3.perform返回ResultActions，返回操作结果，通过ResultActions，提供了统一的验证方式
     * 4.使用StatusResultMatchers对请求结果进行验证
     * 5.使用ContentResultMatchers对请求返回的内容进行验证
      */
    private MockMvc mockMvc;


//   @Before在每个方法执行前都执行一遍
    @Before
    public void setUp() throws Exception{
        mockMvc= MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getHello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}


```
![](https://ws1.sinaimg.cn/large/007jVdWKgy1fydcaq1ctpj31cw0ei16l.jpg)
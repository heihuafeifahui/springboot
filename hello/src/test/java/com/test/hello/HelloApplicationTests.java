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


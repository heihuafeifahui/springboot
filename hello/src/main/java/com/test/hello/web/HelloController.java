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
     * @ResponseBody 将controller的方法返回的对象通过转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML(这里是JSON)
     * @GetMapping Get的方式请求http映射
     */
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

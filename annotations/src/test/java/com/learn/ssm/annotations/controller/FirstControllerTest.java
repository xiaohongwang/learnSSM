package com.learn.ssm.annotations.controller;

import com.learn.ssm.annotations.util.HttpUtil;
import org.apache.catalina.core.ApplicationContext;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@SpringBootTest(classes = FirstController.class) //创建整个应用程序上下文
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class FirstControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private static final Logger log = LoggerFactory.getLogger(FirstControllerTest.class);

    @Before
    public void setup(){
        this.mvc
                 = MockMvcBuilders.webAppContextSetup(this.context).build();
        //获取sessiokey 信息
         String msg =   HttpUtil.sendRequest("http://192.168.0.201:8010/singlequiz/queryMatchs", "{\"sessionKey\":-1}");
         log.info(msg);
    }

    @Test
    public void needAdviceControllerTest() throws Exception {
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/first/needAdviceController/1")
                .accept(MediaType.APPLICATION_JSON)
                ).andReturn();
       log.info( result.getResponse().getContentAsString());
    }


}
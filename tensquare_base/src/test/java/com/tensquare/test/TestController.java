package com.tensquare.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${supername}")
    private String name;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String name(){
        return name;
    }
}
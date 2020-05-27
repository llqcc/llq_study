package com.liada.microcloud.controller;

import javax.annotation.Resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liada.api.Dept;
import com.liada.microcloud.service.IDeptService;

@RestController
public class DeptController {
    @Resource
    private IDeptService deptService;

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getFallback")    // 如果当前调用的get()方法出现了错误，则执行fallback
    public String list() {
        if(this.deptService.list().equals("2")){
            throw new RuntimeException("部门信息不存在！");
        }
        return "err";
    }

    public String getFallback() {    // 此时方法的参数 与get()一致
        return "helloerror";
    }


}

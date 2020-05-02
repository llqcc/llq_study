package com.liada.microcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liada.api.Dept;
import com.liada.service.IDeptClientService;

@RestController
public class ConsumerDeptController {
	@Resource
	private IDeptClientService deptService ;
	@RequestMapping(value = "/consumer/dept/get")
	public Object getDept(long id) {
		return this.deptService.get(id);
	}
	@RequestMapping(value = "/consumer/dept/list")
	public Object listDept() {
		System.out.println("3333333333");
		return this.deptService.list();
	}
	@RequestMapping(value = "/consumer/dept/add")
	public Object addDept(Dept dept) throws Exception {
		return this.deptService.add(dept);
	}
}

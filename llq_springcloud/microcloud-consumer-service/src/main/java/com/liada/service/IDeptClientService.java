package com.liada.service;

import java.util.List;

import com.liada.service.fallback.IDeptClientServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liada.api.Dept;
import com.liada.commons.config.FeignClientConfig;
@FeignClient(value="MICROCLOUD-ZUUL-GATEWAY",configuration=FeignClientConfig.class,fallbackFactory = IDeptClientServiceFallbackFactory.class)
public interface IDeptClientService {
	@RequestMapping(method=RequestMethod.GET,value="/liada-proxy/dept-proxy/dept/get/{id}")
	public Dept get(@PathVariable("id") long id) ;
	@RequestMapping(method=RequestMethod.GET,value="/liada-proxy/dept-proxy/dept/list")
	public String list() ;
	@RequestMapping(method=RequestMethod.POST,value="/liada-proxy/dept-proxy/dept/add")
	public boolean add(Dept dept) ;
}

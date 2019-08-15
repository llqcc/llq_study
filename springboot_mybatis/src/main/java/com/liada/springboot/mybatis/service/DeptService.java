package com.liada.springboot.mybatis.service;

import java.util.List;

import com.liada.springboot.mybatis.entities.Dept;

public interface DeptService {
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
}

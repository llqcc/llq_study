package com.liada.microcloud.service;

import java.util.List;

import com.liada.api.Dept;

public interface IDeptService {
	public Dept get(long id);

	public boolean add(Dept dept);

	public List<Dept> list();
}

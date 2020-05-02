package com.liada.microcloud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liada.api.Dept;
import com.liada.microcloud.dao.IDeptDao;
import com.liada.microcloud.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public Dept get(long id) {
		return deptDao.findById(id);
	}

	@Override
	public boolean add(Dept dept) {
		return deptDao.doCreate(dept);
	}

	@Override
	public List<Dept> list() {
		return deptDao.findAll();
	}

}

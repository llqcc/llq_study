package com.liada.microcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.liada.api.Dept;
@Mapper
public interface IDeptDao {
	public boolean doCreate(Dept no);
	public Dept findById(Long id);
	public List<Dept> findAll();
}

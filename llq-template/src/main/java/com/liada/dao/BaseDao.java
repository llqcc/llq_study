package com.liada.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("Dao")
@Transactional
public class BaseDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * <p>描述:保存</p>
	 * @return	返回改变的行数
	 */
	public int save(String str, Object obj) throws Exception {
		Object o = sqlSessionTemplate.insert(str, obj);
		return Integer.parseInt(o.toString());
	}
	/**
	 * <p>描述:更新</p>
	 * @return	返回改变的行数
	 */
	public int update(String str, Object obj) throws Exception {
		Object o = sqlSessionTemplate.update(str, obj);
		return Integer.parseInt(o.toString());
	}
	/**
	 * <p>描述:删除</p>
	 * @return	返回改变的行数
	 */
	public int delete(String str, Object obj) throws Exception {
		Object o = sqlSessionTemplate.delete(str, obj);
		return Integer.parseInt(o.toString());
	}
	/**
	 * <p>描述: 查询一个值.比如查行数 count(*)	的结果</p>
	 * @return	返回改变的行数
	 */
	public int findForNum(String str, Object obj) throws Exception {
		String s = sqlSessionTemplate.selectOne(str, obj).toString();
		return Integer.parseInt(s.substring(s.indexOf("=") + 1, s.length() - 1));
	}
	/**
	 * <p>描述:查询单个结果</p>
	 * @return	返回改变的行数
	 */
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}
	/**
	 * <p>描述:查询多个结果</p>
	 * @return	返回改变的行数
	 */
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}

}

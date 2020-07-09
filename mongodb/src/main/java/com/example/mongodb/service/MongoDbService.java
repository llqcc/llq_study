package com.example.mongodb.service;

import com.example.mongodb.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author llq
 * @date 2020.06.14 16:21
 */
@Service
public class MongoDbService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     *
     * @param Dept
     * @return
     */
    public String saveObj(Dept dept) {
        mongoTemplate.save(dept);
        return "添加成功";
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Dept> findAll() {
        return mongoTemplate.findAll(Dept.class);
    }

    /***
     * 根据id查询
     * @param id
     * @return
     */
    public Dept getDeptById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Dept.class);
    }

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    public Dept getDeptByName(String name) {
        Query query = new Query(Criteria.where("dname").is(name));
        return mongoTemplate.findOne(query, Dept.class);
    }

    /**
     * 更新对象
     *
     * @param Dept
     * @return
     */
    public String updateDept(Dept Dept) {
        Query query = new Query(Criteria.where("_id").is(Dept.getId()));
        Update update = new Update().set("dname", Dept.getDname()).set("loc", Dept.getLoc());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Dept.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Dept.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Dept.class);
        return "success";
    }

    /***
     * 删除对象
     * @param Dept
     * @return
     */
    public String deleteDept(Dept Dept) {
        mongoTemplate.remove(Dept);
        return "success";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public String deleteDeptById(String id) {
        // findOne
        Dept Dept = getDeptById(id);
        // delete
        deleteDept(Dept);
        return "success";
    }

    /**
     * 模糊查询
     *
     * @param search
     * @return
     */
    public List<Dept> findByLikes(String search) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //criteria.where("name").regex(search);
        Pattern pattern = Pattern.compile("^.*" + search + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.where("dname").regex(pattern);
        List<Dept> lists = mongoTemplate.findAllAndRemove(query, Dept.class);
        return lists;
    }


}

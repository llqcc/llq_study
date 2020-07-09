package com.llq.mongodb.controller;

import com.llq.mongodb.entity.Dept;
import com.llq.mongodb.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author llq
 * @date 2020.06.14 16:59
 */
@RestController
public class DeptController {

    @Autowired
    MongoDbService mongoDbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Dept dept) {
        return mongoDbService.saveObj(dept);
    }

    @GetMapping("/mongo/findAll")
    public List<Dept> findAll() {
        return mongoDbService.findAll();
    }

    @GetMapping("/mongo/findOne")
    public Dept findOne(@RequestParam String id) {
        return mongoDbService.getDeptById(id);
    }

    @GetMapping("/mongo/findOneByName")
    public Dept findOneByName(@RequestParam String name) {
        return mongoDbService.getDeptByName(name);
    }

    @PostMapping("/mongo/update")
    public String update(@RequestBody Dept Dept) {
        return mongoDbService.updateDept(Dept);
    }

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody Dept Dept) {
        return mongoDbService.deleteDept(Dept);
    }

    @GetMapping("/mongo/delById")
    public String delById(@RequestParam String id) {
        return mongoDbService.deleteDeptById(id);
    }

    @GetMapping("/mongo/findlikes")
    public List<Dept> findByLikes(@RequestParam String search) {
        return mongoDbService.findByLikes(search);
    }

}

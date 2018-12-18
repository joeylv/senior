package com.joey.core.controller;

import com.joey.core.entity.User;
import com.joey.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    MQService service;

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public void update(@RequestParam User user) {
        userService.update(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/select")
    public User select(@PathVariable("id") int id) {
        return userService.selectById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        return userService.selectById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/selectAll/{pageNum}/{pageSize}")
    public List<User> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.selectAll(pageNum, pageSize);
    }

}


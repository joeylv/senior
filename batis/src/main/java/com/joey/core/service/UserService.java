package com.joey.core.service;

import com.joey.core.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /**
     * 删除
     */
    void delete(int id);

    /**
     * 增加
     */
    void insert(User user);

    /**
     * 更新
     */
    int update(User user);

    User getUser(String msg);

    /**
     * 查询单个
     */
    User selectById(int id);

    /**
     * 查询全部列表
     */
    List<User> selectAll(int pageNum, int pageSize);
}

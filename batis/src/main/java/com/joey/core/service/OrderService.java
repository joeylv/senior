package com.joey.core.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void createOrder(JSONObject orderInfo) throws Exception;

    void saveLocalMsg(JSONObject orderInfo) throws Exception;
}

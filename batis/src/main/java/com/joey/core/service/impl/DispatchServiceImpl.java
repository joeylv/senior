package com.joey.core.service.impl;

import com.joey.core.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class DispatchServiceImpl implements DispatchService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void dispatch(String orderId) throws Exception {
        String sql = "insert into table_dispatch (dispatch_seq,order_id,dispatch_context ) values(UUID(),?,?)";
        int count = jdbcTemplate.update(sql, orderId, "");
        if (count != 1) {
            throw new SQLException("调度创建失败,[原因]数据库操作失败");
        }


    }
}

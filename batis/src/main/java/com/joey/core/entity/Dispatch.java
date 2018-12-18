package com.joey.core.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "table_dispatch")
public class Dispatch {
    @Column(name = "dispatch_seq")
    private String dispatchSeq;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "dispatch_context")
    private String dispatchContext;

    /**
     * @return dispatch_seq
     */
    public String getDispatchSeq() {
        return dispatchSeq;
    }

    /**
     * @param dispatchSeq
     */
    public void setDispatchSeq(String dispatchSeq) {
        this.dispatchSeq = dispatchSeq;
    }

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return dispatch_context
     */
    public String getDispatchContext() {
        return dispatchContext;
    }

    /**
     * @param dispatchContext
     */
    public void setDispatchContext(String dispatchContext) {
        this.dispatchContext = dispatchContext;
    }
}
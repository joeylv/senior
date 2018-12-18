package com.joey.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_distributed_message")
public class Message {
    @Id
    @Column(name = "unique_id")
    private Integer uniqueId;

    @Column(name = "msg_status")
    private Byte msgStatus;

    /**
     * @return unique_id
     */
    public Integer getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId
     */
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return msg_status
     */
    public Byte getMsgStatus() {
        return msgStatus;
    }

    /**
     * @param msgStatus
     */
    public void setMsgStatus(Byte msgStatus) {
        this.msgStatus = msgStatus;
    }
}
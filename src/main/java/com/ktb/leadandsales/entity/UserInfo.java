package com.ktb.leadandsales.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Column(name = "ID") @Id @GeneratedValue
    public Integer id;

    @Column(name = "LINE_ID")
    public String lineId;

    @Column(name = "LINE_TYPE")
    public String lineType;

    @Column(name="GROUP_ID")
    private Integer groupId;

    @Column(name = "RECORD_STATUS")
    private Boolean recordStatus;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private Date createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private Date updatedDt;

}

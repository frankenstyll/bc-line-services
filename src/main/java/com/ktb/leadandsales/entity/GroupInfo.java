package com.ktb.leadandsales.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GROUP_INFO")
public class GroupInfo {
    @Column(name = "GROUP_ID") @Id @GeneratedValue
    public Integer groupId;

    @Column(name = "GROUP_NAME")
    public String groupName;
}

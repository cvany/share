package com.fpx.pds.wms.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Rule {
    private Integer id;

    private Integer groupid;

    private Integer personid;

    private String name;

    private String weekstatus;

    private String weekendstatus;

    private Date createtime;

    private Date modifytime;

}
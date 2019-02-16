/*
 * Copyright (c) 2005-2018.  FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */

package com.fpx.pds.track;

import lombok.Data;

import java.io.Serializable;

/**
 * 业务数据
 *
 * @author: cuiwy
 * @date: 2019/1/23
 * @since: 1.0.0
 */
@Data
public class BizDTO implements Serializable {
    /**
     * 4px跟踪号
     */
    private String tbs_referenceno;
    /**
     * 四方格跟踪单号
     */
    private String tbs_shippercode;
    /**
     * 是否开始抓取轨迹
     */
    private boolean beginTrack;
    /**
     * xms 系统配置的客户code
     */
    private String ctm_id;
    /**
     * 服务商单号
     */
    private String tbs_servecode;
    /**
     * 起始地区二字码
     */
    private String ct_code_start;
    /**
     * 目的地区二字码
     */
    private String ct_code;
    /**
     * 卖家[客户]code
     */
    private String tbs_referenceshippercode;
    /**
     * 产品code
     */
    private String ref_code;
    /**
     * 渠道代码
     */
    private String sn_code;
}

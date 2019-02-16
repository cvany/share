/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.track;

import lombok.Data;

import java.io.Serializable;

/**
 * 轨迹传输DTO
 *
 * @Author: cuiwy
 * @Date: 2019/1/23 16:02
 * @version: v1.0.0
 */
@Data
public class TrackDTO implements Serializable {
    /**
     * 4px跟踪号（必传）
     */
    private String fpxTrackNo;
    /**
     * 回传节点（必传）
     */
    private String tk_code;
    /**
     * 轨迹发生时间(yyyy-MM-dd HH:mm:ss)（必传）
     */
    private String trk_occurdate;
    /**
     * 轨迹内容
     */
    private String trk_servicedescription;
    /**
     * 轨迹发生地点
     */
    private String trk_areadescription;
    /**
     * 轨迹创建人
     */
    private String trk_createperson;
    /**
     * 服务商单号
     */
    private String serveHawbCode;
    /**
     * 轨迹产生时区
     */
    private String spTrackTimezone;
    /**
     * 签收人
     */
    private String trk_signatory;
    /**
     * 是否需要标准化（默认为true）
     */
    private String needToStandard;
    /**
     * 对接业务系统
     */
    private String system = "GDS-WMS";
    /**
     * 原始轨迹code
     */
    private String spTkcode;
    /**
     * 操作种类（A老系统导入、B业务系统）
     */
    private String mst_code;

}

/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.track;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 业务数据与轨迹DTO
 *
 * @Author: cuiwy
 * @Date: 2019/1/23 17:49
 * @version: v1.0.0
 */
@Data
public class BizAndTrackDTO implements Serializable {
    /**
     * 业务数据
     */
    private BizDTO trackingBusinessPo;
    /**
     * 轨迹列表
     */
    private List<TrackDTO> trackPoLs;
}

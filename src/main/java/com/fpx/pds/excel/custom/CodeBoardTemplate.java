package com.fpx.pds.excel.custom;

import lombok.Data;

/**
 * 码板模板实体
 *
 * @Author: cuiwy
 * @Date: 2019/7/10 14:38
 * @version: v1.0.0
 */
@Data
public class CodeBoardTemplate {
    /**
     * 码板号
     */
    private String codeBoard;
    /**
     * 分拨仓
     */
    private String distributionWh;

    /**
     * 目的站点
     */
    private String destinationSite;

    /**
     * 包裹数量
     */
    private String parcelNum;

    /**
     * 包裹总重量
     */
    private String parcelWeight;

    /**
     * 完成码板时间
     */
    private String finishTime;
}

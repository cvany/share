/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.track;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ObjectUtils;

/**
 * @Author: cuiwy
 * @Date: 2019/1/26 14:00
 * @version: v1.0.0
 */
public class Test {

    public static void main(String[] args){
//     String json ="{\"trackPoLs\":[{\"beginTrack\":false,\"ctm_id\":\"12\",\"system\":\"GDS-WMS\",\"tbs_referenceno\":\"FPX201901240003\",\"tk_code\":\"入库\",\"trk_occurdate\":\"2019-01-26 11:47:22\",\"trk_servicedescription\":\"包裹在TR2333入库\"}],\"trackingBusinessPo\":{\"beginTrack\":false,\"ct_code\":\"asd\",\"ct_code_start\":\"asdsd\",\"ctm_id\":\"12\",\"ref_code\":\"productCode\",\"sn_code\":\"asdsad\",\"tbs_referenceno\":\"FPX201901240003\",\"tbs_referenceshippercode\":\"dseqw21\",\"tbs_servecode\":\"qw\",\"tbs_shippercode\":\"FPX201901240003\"}}";
     String json2="{\"beginTrack\":false,\"fpxTrackNo\":\"fpx2019012410336\",\"system\":\"GDS-WMS\",\"tk_code\":\"航班起飞\",\"trk_occurdate\":\"2019-02-15 16:08:09\",\"trk_servicedescription\":\"航班起飞\"}";
//        TrackDTO bizAndTrackDTO = JSON.parseObject(json, TrackDTO.class);
        BizAndTrackDTO bizAndTrackDTO = JSON.parseObject(json2, BizAndTrackDTO.class);
        System.out.println(bizAndTrackDTO.getTrackingBusinessPo());
        if (null ==bizAndTrackDTO.getTrackingBusinessPo()){
            System.out.println("这是根据4px回传轨迹");
        }
        System.out.println(bizAndTrackDTO.toString());
    }
}

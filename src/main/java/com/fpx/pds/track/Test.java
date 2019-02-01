/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.track;

import com.alibaba.fastjson.JSON;

/**
 * @Author: cuiwy
 * @Date: 2019/1/26 14:00
 * @version: v1.0.0
 */
public class Test {

    public static void main(String[] args){
     String json ="{\"trackPoLs\":[{\"beginTrack\":false,\"ctm_id\":\"12\",\"system\":\"GDS-WMS\",\"tbs_referenceno\":\"FPX201901240003\",\"tk_code\":\"入库\",\"trk_occurdate\":\"2019-01-26 11:47:22\",\"trk_servicedescription\":\"包裹在TR2333入库\"}],\"trackingBusinessPo\":{\"beginTrack\":false,\"ct_code\":\"asd\",\"ct_code_start\":\"asdsd\",\"ctm_id\":\"12\",\"ref_code\":\"productCode\",\"sn_code\":\"asdsad\",\"tbs_referenceno\":\"FPX201901240003\",\"tbs_referenceshippercode\":\"dseqw21\",\"tbs_servecode\":\"qw\",\"tbs_shippercode\":\"FPX201901240003\"}}";
        BizAndTrackDTO bizAndTrackDTO = JSON.parseObject(json, BizAndTrackDTO.class);
        System.out.println(bizAndTrackDTO.toString());
    }
}

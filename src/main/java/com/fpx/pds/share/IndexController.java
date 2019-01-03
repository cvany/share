/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: cuiwy
 * @Date: 2018/10/10 17:06
 * @version: v1.0.0
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }
}

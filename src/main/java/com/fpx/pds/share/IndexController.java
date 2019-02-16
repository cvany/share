
package com.fpx.pds.share;

import com.fpx.pds.track.BizAndTrackDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: cuiwy
 * @Date: 2018/10/10 17:06
 * @version: v1.0.0
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    /**
     * 回传业务数据与N 条轨迹
     *
     * @return
     */
    @PostMapping(value = "/track", headers = {"Content-Type=application/json"})
    public String addBizAndTrack(@RequestBody BizAndTrackDTO dto) {
        System.out.println(dto);
        return "";
    }
}

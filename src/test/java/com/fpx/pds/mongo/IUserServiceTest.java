package com.fpx.pds.mongo;

import com.fpx.pds.ShareApplicationTests;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 09:46
 * @version: v1.0.0
 */
public class IUserServiceTest extends ShareApplicationTests {

    @Resource
    IUserService iUserService;

    @Test
    public void save() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("战士" + i);
            user.setAge(1 * i);
            ArrayList<String> list = Lists.newArrayList();
            list.add("打篮球" + i);
            list.add("跑步" + i);
            user.setHobbies(list);
            ArrayList<String> list2 = Lists.newArrayList();
            list2.add("广东" + i);
            user.setAddr(list2);
            iUserService.save(user);
        }
    }

    @Test
    public void page() {
        UserVO vo = new UserVO();
        vo.setPageNum(1);
        vo.setPageSize(10);
        PageResult<User> page = iUserService.page(vo);
        System.out.println(page.getList().size());
    }
}
/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 09:44
 * @version: v1.0.0
 */
public interface IUserService {

    /**
     * 保存
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 分页
     *
     * @param vo
     * @return
     */
    PageResult<User> page(UserVO vo);
}

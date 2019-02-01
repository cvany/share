/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 09:45
 * @version: v1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserRepository iUserRepository;
    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public PageResult<User> page(UserVO vo) {
        MongoPageHelper helper = new MongoPageHelper(mongoTemplate);
        return helper.pageQuery(vo, User.class, vo.getPageSize(), vo.getPageNum());
    }
}

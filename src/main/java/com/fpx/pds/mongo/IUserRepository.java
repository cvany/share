/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: cuiwy
 * @Date: 2019/2/1 09:41
 * @version: v1.0.0
 */
public interface IUserRepository extends MongoRepository<User, String> {
}

package com.fpx.pds.map.address;

import com.fpx.pds.utils.StringUtil;
import org.springframework.util.Assert;

/**
 * 地址处理工厂
 *
 * @Author: cuiwy
 * @Date: 2019/7/19 10:12
 * @version: v1.0.0
 */
public class AddressHandlerFactory {

    /**
     * 地址处理
     *
     * @param address
     * @return
     */
    public static String handle(String address) {
        AddressHandler addressHandler;
        Assert.isNull(address, "address must not be null");
        if (StringUtil.isEnglishSentence(address)) {
            addressHandler = EnglishAddressHandler.getInstance();
        } else {
            addressHandler = ChineseAddressHandler.getInstance();
        }
        return addressHandler.handle(address);
    }
}

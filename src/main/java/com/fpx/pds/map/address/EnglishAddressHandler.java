package com.fpx.pds.map.address;

/**
 * 英文地址处理者
 *
 * @Author: cuiwy
 * @Date: 2019/7/19 14:57
 * @version: v1.0.0
 */
public class EnglishAddressHandler implements AddressHandler {
    @Override
    public String handle(String address) {
        return null;
    }

    private static class EnglishAddressHandlerInstance {
        private final static EnglishAddressHandler instance = new EnglishAddressHandler();
    }

    private EnglishAddressHandler() {
    }

    public static EnglishAddressHandler getInstance() {
        return EnglishAddressHandlerInstance.instance;
    }
}

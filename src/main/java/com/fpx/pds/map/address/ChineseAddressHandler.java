package com.fpx.pds.map.address;

import com.fpx.pds.utils.StringUtil;

/**
 * 中文地址处理者
 *
 * @Author: cuiwy
 * @Date: 2019/7/19 14:58
 * @version: v1.0.0
 */
public class ChineseAddressHandler implements AddressHandler {
    /**
     * 默认规则处理引擎
     */
    private RuleEngine ruleEngine = new ChineseRuleEngine();

    /**
     * 处理
     *
     * @param address
     * @return
     */
    @Override
    public String handle(String address) {
        String charFilter = StringUtil.specialCharFilter(address);
        //交给规则引擎
        return ruleEngine.launch(address);
    }

    private static class ChineseAddressHandlerInstance {
        private final static ChineseAddressHandler instance = new ChineseAddressHandler();
    }

    private ChineseAddressHandler() {
    }

    public static ChineseAddressHandler getInstance() {
        return ChineseAddressHandlerInstance.instance;
    }

}

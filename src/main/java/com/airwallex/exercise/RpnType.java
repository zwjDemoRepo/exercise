package com.airwallex.exercise;

/**
 * Created by Administrator on 2017/11/11 0011.
 * 枚举定义
 */
public enum RpnType {

    Operand("操作数"),
    Operator("操作符");
    private String description;
    RpnType(String description) {
        this.description = description;
    }
}
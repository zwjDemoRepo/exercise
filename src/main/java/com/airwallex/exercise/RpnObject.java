package com.airwallex.exercise;

/**
 * Created by Administrator on 2017/11/11 0011.
 * 构造操作表达式元素实体
 */
//
public class RpnObject {

    private Object operand;

    private RpnType type;

    public RpnObject(){

    }

    public RpnObject(Object operand, RpnType type) {
        this.operand = operand;
        this.type = type;
    }

    public Object getOperand() {
        return operand;
    }

    public void setOperand(Object operand) {
        this.operand = operand;
    }

    public RpnType getType() {
        return type;
    }

    public void setType(RpnType type) {
        this.type = type;
    }

}

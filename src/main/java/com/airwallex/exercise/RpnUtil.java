package com.airwallex.exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2017/11/11 0011.
 */
//3.运算处理工具类
public class RpnUtil {
    /**
     * +
     */
    private final static String OPAdd = "+";
    /**
     * -
     */
    private final static String OPSubtraction = "-";
    /**
     * *
     */
    private final static String OPMultiplication = "*";
    /**
     * /
     */
    private final static String OPDivision = "/";
    /**
     * sqrt
     */
    private final static String OPSqrt = "sqrt";
    /**
     * undo
     */
    private final static String OPUndo = "undo";

    /**
     * clear
     */
    private final static String OPClear = "clear";

    //表达式
    private String exp;

    // 精确到小数点后15位
    private int precision = 15;

    // 取舍模式
    private RoundingMode roundingMode = RoundingMode.HALF_UP;

    // 存放逆波兰表达式
    private List<RpnObject> rpnList = new ArrayList<RpnObject>();

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public List<RpnObject> getRpnList() {
        return rpnList;
    }

    public void setRpnList(List<RpnObject> rpnList) {
        this.rpnList = rpnList;
    }

    /**
     * @param exp
     *            四则运算中缀表达式
     * @param precision
     *            精确到小数点后位数
     * @param roundingMode
     *            取舍模式
     */
    public RpnUtil(String exp, int precision, RoundingMode roundingMode) {
        this.exp = exp;
        this.precision = precision;
        this.roundingMode = roundingMode;
        parse();

    }
    /**
     * 分析四则运算表达式，将数字与运算符进行分解
     */
    private void parse() {

    }

    /**
     * 判断当前字符或字符串是否是操作符
     *
     * @param str
     * @return boolean
     */
    private boolean isOperator(String str) {
        return str.startsWith(OPAdd) || str.startsWith(OPSubtraction)
                || str.startsWith(OPMultiplication) || str.startsWith(OPDivision)
                || str.startsWith(OPSqrt) || str.startsWith(OPUndo) || str.startsWith(OPClear);
    }

    /**
     * 判断当前字符是否是高优先级运算符 * /
     *
     * @param str
     * @return
     */
    private boolean isHeighOperator(Object str) {
        if (str.equals(OPMultiplication) || str.equals(OPDivision)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过后缀表达式计算结果
     *
     * @return numberStr
     */
    public String calculate() {
        Stack<String> numberStack = new Stack<String>();
        int length = rpnList.size();
        for (int i = 0; i < length; i++) {
            RpnObject temp = rpnList.get(i);
            if (temp.getType()== RpnType.Operand) {
                numberStack.push(temp.getOperand().toString());
            } else {
                BigDecimal tempNumber1 = getBigDecimal(numberStack.pop(),
                        precision, roundingMode);
                BigDecimal tempNumber2 = getBigDecimal(numberStack.pop(),
                        precision, roundingMode);
                BigDecimal tempNumber = getBigDecimal("", precision,
                        roundingMode);
                System.out.println(temp.getOperand().toString());
                if (OPAdd.equals(temp.getOperand())) {
                    tempNumber = tempNumber2.add(tempNumber1);
                } else if (OPSubtraction.equals(temp.getOperand())) {
                    tempNumber = tempNumber2.subtract(tempNumber1);
                } else if (OPMultiplication.equals(temp.getOperand())) {
                    tempNumber = tempNumber2.multiply(tempNumber1);
                } else if (OPDivision.equals(temp.getOperand())) {
                    tempNumber = tempNumber2.divide(tempNumber1, precision,
                            roundingMode);
                }
                numberStack.push(tempNumber.toString());
            }
        }
        return numberStack.pop();
    }

    /**
     * 按精确度计算结果
     *
     * @param numString
     * @param precision
     * @param roundingMode
     * @return
     */
    public static BigDecimal getBigDecimal(String numString, int precision,
                                           RoundingMode roundingMode) {
        String precisionFlag = "0";
        if (numString == null || numString.equals("")) {
            precisionFlag = "0.00";
        } else {
            precisionFlag = numString;
        }
        BigDecimal bigDecimal = new BigDecimal(precisionFlag);
        bigDecimal.setScale(precision, roundingMode);
        return bigDecimal;
    }

}

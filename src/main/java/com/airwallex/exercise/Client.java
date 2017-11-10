package com.airwallex.exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2017/11/11 0011.
 */
public class Client{
    public static void main(String[] args) {
        String str = "1+2/3";
        RpnUtil u = new RpnUtil(str,13,RoundingMode.HALF_UP);
        System.out.println("后缀式");
        for(int i=0;i<u.getRpnList().size();i++){
            System.out.print(u.getRpnList().get(i).getOperand());
        }
        System.out.println();
        System.out.println(u.calculate());
    }
}

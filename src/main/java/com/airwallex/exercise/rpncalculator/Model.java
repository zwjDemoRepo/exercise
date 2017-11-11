package com.airwallex.exercise.rpncalculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.DOWN;
import static java.util.stream.Collectors.joining;

public class Model {

    private static final DecimalFormat BIG_DEC_DISPLAY_FORMAT = new DecimalFormat();

    static {
        BIG_DEC_DISPLAY_FORMAT.setMinimumFractionDigits(0);
        BIG_DEC_DISPLAY_FORMAT.setMaximumFractionDigits(10);
        BIG_DEC_DISPLAY_FORMAT.setRoundingMode(DOWN);
    }

    private Stack<BigDecimal> results;

    public Model() {
        results = new Stack<>();
    }

    public void push(BigDecimal aNumber) {
        results.add(aNumber);
    }

    public void add(BigDecimal... operands) {
        results.push(operands[0].add(operands[1]));
    }

    public void subtract(BigDecimal... operands) {
        results.push(operands[0].subtract(operands[1]));
    }

    public void multiply(BigDecimal... operands) {
        results.push(operands[0].multiply(operands[1]));
    }

    public void divide(BigDecimal... operands) throws RPNCalculatorException {
        if (operands[1].compareTo(ZERO) == 0) {
            throw new RPNCalculatorException("Division by zero.");
        }
        results.push(operands[0].divide(operands[1], 15, DOWN));
    }

    public void sqrt(BigDecimal... operand) throws RPNCalculatorException {
        if (operand[0].compareTo(ZERO) == -1) {
            throw new RPNCalculatorException("Square root of negative number is not a real number.");
        }
        results.push(new BigDecimal(Math.sqrt(operand[0].doubleValue())));
    }

    public void clear() {
        results.clear();
    }

    public BigDecimal pop() throws RPNCalculatorException {
        if (results.size() == 0) {
            throw new RPNCalculatorException("Stack is empty.");
        }
        return results.pop();
    }

    public int size() {
        return results.size();
    }

    public String getContents() {
        return results.stream().map(BIG_DEC_DISPLAY_FORMAT::format).collect(joining(" "));
    }
}

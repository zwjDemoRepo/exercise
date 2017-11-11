package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberCommand extends AbstractCommand {

    private static final int MAXIMUM_SCALE = 15;

    public NumberCommand(BigDecimal number, Model aModel) {
        super(aModel);
        setOperands(new BigDecimal[1]);
        getOperands()[0] = number.setScale(MAXIMUM_SCALE, RoundingMode.DOWN);
    }

    @Override
    protected void doExecute() {
        getModel().push(getOperands()[0]);
    }

    public void undo() throws RPNCalculatorException {
        getModel().pop();
    }

    @Override
    protected void init() throws RPNCalculatorException {
        // Nothing to do
    }
}

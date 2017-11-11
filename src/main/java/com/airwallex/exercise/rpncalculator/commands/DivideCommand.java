package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;

import java.math.BigDecimal;

public class DivideCommand extends DoubleOperandCommand {

    public DivideCommand(Model model) {
        super(model);
    }

    @Override
    protected void doExecute() throws RPNCalculatorException {
        BigDecimal[] currentOperands = getOperands();
        getModel().divide(currentOperands[0], currentOperands[1]);

    }
}

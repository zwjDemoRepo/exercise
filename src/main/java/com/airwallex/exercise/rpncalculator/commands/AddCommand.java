package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;

import java.math.BigDecimal;

public class AddCommand extends DoubleOperandCommand {

    public AddCommand(Model model) {
        super(model);
    }

    @Override
    protected void doExecute() throws RPNCalculatorException {
        BigDecimal[] currentOperands = getOperands();
        getModel().add(currentOperands[0], currentOperands[1]);

    }
}

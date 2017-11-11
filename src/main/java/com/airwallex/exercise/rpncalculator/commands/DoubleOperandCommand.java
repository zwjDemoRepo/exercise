package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;

abstract class DoubleOperandCommand extends MathOperationCommand {

    private static final int NUMBER_OF_OPERANDS = 2;

    DoubleOperandCommand(Model model) {
        super(model, NUMBER_OF_OPERANDS);
    }
}

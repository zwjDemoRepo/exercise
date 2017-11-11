package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.RPNCalculatorException;

public interface Command {

    /**
     * Executes command.
     *
     * @throws RPNCalculatorException - if there is a problem executing command
     */
    void execute() throws RPNCalculatorException;

    /**
     * Undoes the effect of latest command execution.
     *
     * @throws RPNCalculatorException - if there is a problem undoing command
     */
    void undo() throws RPNCalculatorException;
}

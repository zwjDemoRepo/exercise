package com.airwallex.exercise.rpncalculator.commands;

import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;

public class ClearCommand extends AbstractCommand {

    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    protected void doExecute() {
        getModel().clear();
    }

    public void undo() {
        // does nothing as there are no undo requirements for clear command.
    }

    @Override
    protected void init() throws RPNCalculatorException {
        // Nothing to do
    }
}

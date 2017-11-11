package com.airwallex.exercise.rpncalculator;


import com.airwallex.exercise.rpncalculator.commands.Command;

import java.util.Stack;

public class Controller {

    private Stack<Command> commandHistory;

    public Controller() {
        commandHistory = new Stack<>();
    }

    public void executeCommand(Command command) throws RPNCalculatorException {
        // Execute the command and add it to the history list
        command.execute();
        commandHistory.add(command);
    }

    public void undo() throws RPNCalculatorException {

        if (commandHistory.size() > 0) {
            // Get the Command object to be undone
            Command command = commandHistory.pop();

            // Execute the Command object's undo method
            command.undo();
        }
    }

    void clearUndoHistory() {
        commandHistory.clear();
    }
}

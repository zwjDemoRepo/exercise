package com.airwallex.exercise.rpncalculator;

import com.airwallex.exercise.rpncalculator.commands.*;
import org.slf4j.Logger;

import java.util.Scanner;
import java.util.regex.MatchResult;

import static org.slf4j.LoggerFactory.getLogger;

public class RPNCalculator {

    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";
    private static final String SUBTRACT = "-";
    private static final String ADD = "+";
    private static final String SQRT = "sqrt";
    private static final String CLEAR = "clear";
    private static final String UNDO = "undo";

    private static final Logger LOGGER = getLogger(RPNCalculator.class);

    private Model model = new Model();
    private Controller controller = new Controller();

    public static void main(String[] args) {
        new RPNCalculator().start();
    }

    private void start() {

        try (Scanner inputScanner = new Scanner(System.in)) {

            while (inputScanner.hasNextLine()) {

                String inputLine = inputScanner.nextLine();
                try (Scanner lineScanner = new Scanner(inputLine)) {
                    while (lineScanner.hasNext()) {
                        performCommands(lineScanner);
                    }
                }
                LOGGER.info("stack: {}", model.getContents());
            }
        }
    }

    private void performCommands(Scanner lineScanner) {
        String token = null;

        try {
            if (lineScanner.hasNextBigDecimal()) {
                controller.executeCommand(new NumberCommand(lineScanner.nextBigDecimal(), model));
            } else {
                token = lineScanner.next();

                if (ADD.equals(token)) {
                    controller.executeCommand(new AddCommand(model));
                } else if (SUBTRACT.equals(token)) {
                    controller.executeCommand(new SubtractCommand(model));
                } else if (MULTIPLY.equals(token)) {
                    controller.executeCommand(new MultiplyCommand(model));
                } else if (DIVIDE.equals(token)) {
                    controller.executeCommand(new DivideCommand(model));
                } else if (SQRT.equals(token)) {
                    controller.executeCommand(new SqrtCommand(model));
                } else if (CLEAR.equals(token)) {
                    controller.executeCommand(new ClearCommand(model));
                    controller.clearUndoHistory();
                } else if (UNDO.equals(token)) {
                    controller.undo();
                } else {
                    LOGGER.warn("Invalid operator ignoring ...");
                }
            }
        } catch (RPNCalculatorException e) {
            MatchResult mr = lineScanner.match();
            LOGGER.warn("operator {} (position: {}): insufficient parameters", token, mr.end());
        }
    }
}

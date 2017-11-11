package com.airwallex.exercise.rpncalculator.commands;

import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;
import com.airwallex.exercise.rpncalculator.commands.Command;
import com.airwallex.exercise.rpncalculator.commands.SqrtCommand;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SqrtCommandTest {

    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test(expected = NullPointerException.class)
    public void executeWithNullModel() throws RPNCalculatorException {
        Command cmd = new SqrtCommand(null);
        cmd.execute();
    }

    @Test(expected = NullPointerException.class)
    public void undoWithNullModel() throws RPNCalculatorException {
        Command cmd = new SqrtCommand(null);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new SqrtCommand(model);
        cmd.execute();
    }

    @Test(expected = RPNCalculatorException.class)
    public void undoWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new SqrtCommand(model);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithInsuficientOperands() throws RPNCalculatorException {
        Command cmd = new SqrtCommand(model);
        cmd.execute();
    }

    @Test
    public void executeSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(9));
        Command cmd = new SqrtCommand(model);
        cmd.execute();
        assertThat(model.getContents()).isEqualTo("3");
    }

    @Test
    public void executeAndUndoSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(9));
        Command cmd = new SqrtCommand(model);
        cmd.execute();
        cmd.undo();
        assertThat(model.getContents()).isEqualTo("9");
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeSqrtOfNegativeNumber() throws RPNCalculatorException {
        model.push(new BigDecimal(-9));
        Command cmd = new SqrtCommand(model);
        cmd.execute();
    }
}

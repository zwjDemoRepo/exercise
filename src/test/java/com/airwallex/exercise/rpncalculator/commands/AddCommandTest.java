package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCommandTest {

    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test(expected = NullPointerException.class)
    public void executeWithNullModel() throws RPNCalculatorException {
        Command cmd = new AddCommand(null);
        cmd.execute();
    }

    @Test(expected = NullPointerException.class)
    public void undoWithNullModel() throws RPNCalculatorException {
        Command cmd = new AddCommand(null);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new AddCommand(model);
        cmd.execute();
    }

    @Test(expected = RPNCalculatorException.class)
    public void undoWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new AddCommand(model);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithInsuficientOperands() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        Command cmd = new AddCommand(model);
        cmd.execute();
    }

    @Test
    public void executeSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        model.push(new BigDecimal(1));
        Command cmd = new AddCommand(model);
        cmd.execute();
        assertThat(model.getContents()).isEqualTo("2");
    }

    @Test
    public void executeAndUndoSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        model.push(new BigDecimal(1));
        Command cmd = new AddCommand(model);
        cmd.execute();
        cmd.undo();
        assertThat(model.getContents()).isEqualTo("1 1");
    }

}

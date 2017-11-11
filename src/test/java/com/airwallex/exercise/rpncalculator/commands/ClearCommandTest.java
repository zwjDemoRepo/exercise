package com.airwallex.exercise.rpncalculator.commands;


import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;
import com.airwallex.exercise.rpncalculator.commands.ClearCommand;
import com.airwallex.exercise.rpncalculator.commands.Command;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ClearCommandTest {

    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test(expected = NullPointerException.class)
    public void executeWithNullModel() throws RPNCalculatorException {
        model = null;
        Command cmd = new ClearCommand(model);
        cmd.execute();
    }

    @Test
    public void undoWithNullModel() throws RPNCalculatorException {
        model = null;
        Command cmd = new ClearCommand(model);
        cmd.undo();
        assertThat(model).isNull();
    }

    @Test
    public void executeWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new ClearCommand(model);
        cmd.execute();
        assertThat(model.size()).isEqualTo(0);
    }

    @Test
    public void undoWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new ClearCommand(model);
        cmd.undo();
        assertThat(model.size()).isEqualTo(0);
    }

    @Test
    public void executeSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(9));
        model.push(new BigDecimal(3));
        Command cmd = new ClearCommand(model);
        cmd.execute();
        assertThat(model.size()).isEqualTo(0);
    }

    @Test
    public void executeAndUndoSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(9));
        model.push(new BigDecimal(3));
        Command cmd = new ClearCommand(model);
        cmd.execute();
        cmd.undo();
        assertThat(model.size()).isEqualTo(0);
    }
}

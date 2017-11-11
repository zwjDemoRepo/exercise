package rpncalculator;

import com.airwallex.exercise.rpncalculator.Controller;
import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;
import com.airwallex.exercise.rpncalculator.commands.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RPNCalculatorTest {

    private Controller controller;
    private Model model;

    @Before
    public void setUp() {
        model = new Model();
        controller = new Controller();
    }

    @Test
    public void sampleTest1() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        assertThat(model.getContents()).isEqualTo("5 2");
    }

    @Test
    public void sampleTest2() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        controller.executeCommand(new SqrtCommand(model));
        assertThat(model.getContents()).isEqualTo("1.4142135623");
        controller.executeCommand(new ClearCommand(model));
        controller.executeCommand(new NumberCommand(new BigDecimal(9), model));
        controller.executeCommand(new SqrtCommand(model));
        assertThat(model.getContents()).isEqualTo("3");
    }

    @Test
    public void sampleTest3() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        controller.executeCommand(new SubtractCommand(model));
        assertThat(model.getContents()).isEqualTo("3");
        controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
        controller.executeCommand(new SubtractCommand(model));
        assertThat(model.getContents()).isEqualTo("0");
        controller.executeCommand(new ClearCommand(model));
        assertThat(model.getContents()).isEqualTo("");
    }

    @Test
    public void sampleTest4() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(4), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        assertThat(model.getContents()).isEqualTo("5 4 3 2");

        controller.undo();
        controller.undo();
        controller.executeCommand(new MultiplyCommand(model));
        assertThat(model.getContents()).isEqualTo("20");

        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        controller.executeCommand(new MultiplyCommand(model));
        assertThat(model.getContents()).isEqualTo("100");

        controller.undo();
        assertThat(model.getContents()).isEqualTo("20 5");
    }

    @Test
    public void sampleTest5() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(7), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(12), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        controller.executeCommand(new DivideCommand(model));
        assertThat(model.getContents()).isEqualTo("7 6");

        controller.executeCommand(new MultiplyCommand(model));
        assertThat(model.getContents()).isEqualTo("42");

        controller.executeCommand(new NumberCommand(new BigDecimal(4), model));
        controller.executeCommand(new DivideCommand(model));
        assertThat(model.getContents()).isEqualTo("10.5");
    }

    @Test
    public void sampleTest6() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(1), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(4), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        assertThat(model.getContents()).isEqualTo("1 2 3 4 5");

        controller.executeCommand(new MultiplyCommand(model));
        assertThat(model.getContents()).isEqualTo("1 2 3 20");

        controller.executeCommand(new ClearCommand(model));
        controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(4), model));
        controller.executeCommand(new SubtractCommand(model));
        assertThat(model.getContents()).isEqualTo("-1");
    }

    @Test
    public void sampleTest7() throws RPNCalculatorException {
        controller.executeCommand(new NumberCommand(new BigDecimal(1), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(4), model));
        controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        assertThat(model.getContents()).isEqualTo("1 2 3 4 5");

        controller.executeCommand(new MultiplyCommand(model));
        controller.executeCommand(new MultiplyCommand(model));
        controller.executeCommand(new MultiplyCommand(model));
        controller.executeCommand(new MultiplyCommand(model));
        assertThat(model.getContents()).isEqualTo("120");
    }

    @Test
    public void sampleTest8() throws RPNCalculatorException {

        assertThatThrownBy(() -> {
            controller.executeCommand(new NumberCommand(new BigDecimal(1), model));
            controller.executeCommand(new NumberCommand(new BigDecimal(2), model));
            controller.executeCommand(new NumberCommand(new BigDecimal(3), model));
            controller.executeCommand(new MultiplyCommand(model));
            controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
            controller.executeCommand(new AddCommand(model));
            controller.executeCommand(new MultiplyCommand(model));
            controller.executeCommand(new MultiplyCommand(model));
            controller.executeCommand(new NumberCommand(new BigDecimal(6), model));
            controller.executeCommand(new NumberCommand(new BigDecimal(5), model));
        }).isInstanceOf(RPNCalculatorException.class).hasMessage("insufficient parameters.");

        assertThat(model.getContents()).isEqualTo("11");
    }

}

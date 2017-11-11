package rpncalculator;

import com.airwallex.exercise.rpncalculator.Model;
import com.airwallex.exercise.rpncalculator.RPNCalculatorException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelTest {

    private Model testModel;

    @Before
    public void setUp() {
        testModel = new Model();
    }

    @Test
    public void testPush() {
        testModel.push(new BigDecimal(1));
        testModel.push(new BigDecimal(2));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("1 2", 2);
    }

    /**
     * Can not happen as we always have a not null object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPushNull() {
        testModel.push(null);
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("null", 1);
    }

    @Test
    public void testAdd() {
        testModel.add(new BigDecimal(1), new BigDecimal(2));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("3", 1);
    }

    @Test
    public void testSubtract() {
        testModel.subtract(new BigDecimal(10), new BigDecimal(5));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("5", 1);
    }

    @Test
    public void testMultiply() {
        testModel.multiply(new BigDecimal(10), new BigDecimal(5));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("50", 1);
    }

    @Test
    public void testDivide() throws RPNCalculatorException {
        testModel.divide(new BigDecimal(10), new BigDecimal(5));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("2", 1);
    }

    @Test(expected = RPNCalculatorException.class)
    public void testDivideByZero() throws RPNCalculatorException {
        testModel.divide(new BigDecimal(10), new BigDecimal(0));
    }

    @Test
    public void testSqrt() throws RPNCalculatorException {
        testModel.sqrt(new BigDecimal(2));
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("1.4142135623", 1);
        //TODO check.
    }

    @Test(expected = RPNCalculatorException.class)
    public void testSqrtOfNegativeNumber() throws RPNCalculatorException {
        testModel.sqrt(new BigDecimal(-2));
    }

    @Test
    public void testClear() {
        testModel.push(new BigDecimal(10));
        testModel.push(new BigDecimal(3));
        testModel.clear();
        assertThat(testModel.size()).isEqualTo(0);
    }

    @Test(expected = RPNCalculatorException.class)
    public void testPopWithEmptyModel() throws RPNCalculatorException {
        testModel.pop();
    }

    @Test
    public void testPop() throws RPNCalculatorException {
        testModel.push(new BigDecimal(10));
        testModel.push(new BigDecimal(3));
        testModel.pop();
        assertThat(testModel).extracting(Model::getContents, Model::size).containsExactly("10", 1);
    }

    @Test
    public void testToStringDisplayingRightNumberOfDecimalPlaces() {
        testModel.push(new BigDecimal(10));
        testModel.push(new BigDecimal(1.1));
        testModel.push(new BigDecimal(-3.12345));
        testModel.push(new BigDecimal(-3.123456789));
        testModel.push(new BigDecimal(-3.1234567890));
        testModel.push(new BigDecimal(-3.01234567890123456789));
        assertThat(testModel).extracting(Model::getContents, Model::size)
                .containsExactly("10 1.1 -3.12345 -3.123456789 -3.123456789 -3.0123456789", 6);
    }
}

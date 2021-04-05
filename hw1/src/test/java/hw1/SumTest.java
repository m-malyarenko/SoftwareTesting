package hw1;

import org.testng.Assert;
import org.testng.annotations.*;

import com.epam.tat.module4.Calculator;

/**
 * Functional Test
 * 
 * Summing operation DDT
 * 
 * Class: Calculator
 * 
 * Methods:
 *  double sum(double, double)
 *  long sum(long, long)
 */

public class SumTest {
    private Calculator calc;

    @DataProvider(name = "sumOperandsDouble")
    public Object[][] sumOperandsDouble() {
        return new Object[][] {
            {"1.2566 899.123", "900.3796"},
            {"22.8 22.8", "45.6"},
            {"0.0 0.0", "0.0"},
            {"123.123 456.456", "579.579"}
        };
    }

    @DataProvider(name = "sumOperandsLong")
    public Object[][] sumOperandsLong() {
        return new Object[][] {
            {"8856465 22565", "8879030"},
            {"0 999999", "999999"},
            {"0 0", "0"},
            {"123456789 123456789", "246913578"}
        };
    }

    @BeforeClass
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "sumOperandsDouble")
    public void testSumDouble(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        double op_a = Double.parseDouble(operandsList[0]);
        double op_b = Double.parseDouble(operandsList[1]);
        double exp_sum = Double.parseDouble(expected);
        double act_sum = calc.sum(op_a, op_b);

        Assert.assertEquals(act_sum, exp_sum, 1e-10, "Delta is 1e-10");
    }

    @Test(dataProvider = "sumOperandsLong")
    public void testSumLong(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        long op_a = Long.parseLong(operandsList[0]);
        long op_b = Long.parseLong(operandsList[1]);
        long exp_sum = Long.parseLong(expected);
        long act_sum = calc.sum(op_a, op_b);

        Assert.assertEquals(act_sum, exp_sum);
    }
}

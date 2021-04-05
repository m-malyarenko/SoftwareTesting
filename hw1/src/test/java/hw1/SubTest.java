package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;

/**
 * Functional Test
 * 
 * Subtraction operation DDT
 * 
 * Class: Calculator
 * 
 * Methods:
 *  double sub(double, double)
 *  long sub(long, long)
 */

public class SubTest {
    private Calculator calc;

    @DataProvider(name = "subOperandsDouble")
    public Object[][] subOperandsDouble() {
        return new Object[][] {
            {"1.2566 899.123", "-897.8664"},
            {"22.8 22.8", "0.0"},
            {"0.0 0.0", "0.0"},
            {"123.123 456.456", "-333.333"},
            {"456.456 123.123", "333.333"}
        };
    }

    @DataProvider(name = "subOperandsLong")
    public Object[][] subOperandsLong() {
        return new Object[][] {
            {"8856465 22565", "8833900"},
            {"0 999999", "-999999"},
            {"0 0", "0"},
            {"902010 1400", "900610"},
            {"1400 902010", "-900610"},
        };
    }

    @BeforeClass
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "subOperandsDouble")
    public void testSubDouble(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        double op_a = Double.parseDouble(operandsList[0]);
        double op_b = Double.parseDouble(operandsList[1]);
        double exp_dif = Double.parseDouble(expected);
        double act_dif = calc.sub(op_a, op_b);

        Assert.assertEquals(exp_dif, act_dif, 1e-10, "Delta is 1e-10");
    }

    @Test(dataProvider = "subOperandsLong")
    public void testSubLong(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        long op_a = Long.parseLong(operandsList[0]);
        long op_b = Long.parseLong(operandsList[1]);
        long exp_dif = Long.parseLong(expected);
        long act_dif = calc.sub(op_a, op_b);

        Assert.assertEquals(act_dif, exp_dif);
    }
}

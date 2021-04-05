package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;

/**
 * Functional Test
 * 
 * Division operation DDT
 * 
 * Class: Calculator
 * 
 * Methods:
 *  double div(double, double)
 *  long div(long, long)
 */

public class DivTest {
    private Calculator calc;

    @DataProvider(name = "divOperandsDouble")
    public Object[][] divOperandsDouble() {
        return new Object[][] {
            {"34.25 0.5", "68.5"},
            {"-22.8 22.8", "-1.0"},
            {"0.0 1.567", "0.0"},
            {"120.7 0.125", "965.6"}
        };
    }

    @DataProvider(name = "divOperandsLong")
    public Object[][] divOperandsLong() {
        return new Object[][] {
            {"8956 3", "2985"},
            {"0 999999", "0"},
            {"100 25", "4"},
            {"22542 34", "663"},
        };
    }

    @BeforeClass
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "divOperandsDouble")
    public void testDivDouble(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        double op_a = Double.parseDouble(operandsList[0]);
        double op_b = Double.parseDouble(operandsList[1]);
        double exp_quot= Double.parseDouble(expected);
        double act_quot = calc.div(op_a, op_b);

        Assert.assertEquals(act_quot, exp_quot, 1e-10, "Delta is 1e-10");
    }

    @Test(dataProvider = "divOperandsLong")
    public void testDivLong(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        long op_a = Long.parseLong(operandsList[0]);
        long op_b = Long.parseLong(operandsList[1]);
        long exp_quot = Long.parseLong(expected);
        long act_quot = calc.div(op_a, op_b);

        Assert.assertEquals(act_quot, exp_quot);
    }
}

package hw1;

import org.testng.annotations.*;
import org.testng.Assert;

import com.epam.tat.module4.Calculator;

/**
 * Functional Test
 * 
 * Multiplication operation DDT
 * 
 * Class: Calculator
 * 
 * Methods:
 *  double mult(double, double)
 *  long mult(long, long)
 */

public class MulTest {
    private Calculator calc;

    @DataProvider(name = "mulOperandsDouble")
    public Object[][] mulOperandsDouble() {
        return new Object[][] {
            {"1.2566 899.123", "1129.837962"},
            {"22.8 22.8", "519.84"},
            {"0.0 0.0", "0.0"},
            {"123.1 456.4", "56182.84"}
        };
    }

    @DataProvider(name = "mulOperandsLong")
    public Object[][] mulOperandsLong() {
        return new Object[][] {
            {"8856 22565", "199835640"},
            {"0 999999", "0"},
            {"0 0", "0"},
            {"902010 1400", "1262814000"},
        };
    }

    @BeforeClass
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "mulOperandsDouble")
    public void testMulDouble(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        double op_a = Double.parseDouble(operandsList[0]);
        double op_b = Double.parseDouble(operandsList[1]);
        double exp_prod = Double.parseDouble(expected);
        double act_prod = calc.mult(op_a, op_b);

        Assert.assertEquals(act_prod, exp_prod, 1e-10, "Delta is 1e-10");
    }

    @Test(dataProvider = "mulOperandsLong")
    public void testMulLong(String operands, String expected) {
        String[] operandsList = operands.split("\s+");

        long op_a = Long.parseLong(operandsList[0]);
        long op_b = Long.parseLong(operandsList[1]);
        long exp_prod = Long.parseLong(expected);
        long act_prod = calc.mult(op_a, op_b);

        Assert.assertEquals(act_prod, exp_prod);
    }
}

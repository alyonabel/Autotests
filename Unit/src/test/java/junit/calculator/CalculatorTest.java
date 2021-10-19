package junit.calculator;

import junit.calculator.Calculator;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Method beforeClass invoked");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Method afterClass invoked");
    }

    @Before
    public void setUp() {
        System.out.println("Method setUp invoked");
    }

    @After
    public void tearDown() {
        System.out.println("Method tearDown invoked");
    }

    @Test
    public void testAddition() {
        System.out.println("Method testAddition invoked");
        int actual = calculator.addition(2, 2);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction() {
        int actual = calculator.subtraction(2, 2);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication() {
        int actual = calculator.multiplication(3, 2);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void testDivision() {
        int actual = calculator.division(9, 3);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @Ignore("ignored")
    public void testDivisionIgnored() {
        System.out.println("Method testDivisionIgnored invoked");
        int actual = calculator.division(9, 3);
        int expected = 3;
        assertEquals(expected, actual);
    }
}
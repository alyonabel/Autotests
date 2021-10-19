package junit.calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int x = 2;
        int y = 2;
        int sum = calculator.addition(x, y);
        System.out.printf("Sum of %d + %d = %d", x, y, sum);
    }
}

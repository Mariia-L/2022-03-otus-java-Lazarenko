package homework.aop;

public class Demo
{
    public static void main(String[] args)
    {
        LoggableCalculator calculator = new CalculatorProxy(new Calculator()).createCalculator();
        calculator.calculate();
        calculator.calculate(5);
        calculator.noLogCalculate(5);
        calculator.calculate(5, 6);

        LoggableCalculator calculatorV2 = new CalculatorProxy(new CalculatorV2()).createCalculator();
        calculatorV2.calculate();
        calculatorV2.calculate(5);
        calculatorV2.noLogCalculate(5);
        calculatorV2.calculate(5, 6);
    }
}
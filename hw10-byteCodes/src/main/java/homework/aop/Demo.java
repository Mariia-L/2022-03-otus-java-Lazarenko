package homework.aop;

public class Demo
{
    public static void main(String[] args)
    {
        LoggableCalculator calculator = CalculatorProxy.createCalculator();
        calculator.calculate(5);
        calculator.noLogCalculate(5);
        calculator.calculate(5, 6);
    }
}
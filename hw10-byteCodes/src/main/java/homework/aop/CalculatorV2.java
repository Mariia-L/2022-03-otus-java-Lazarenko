package homework.aop;

public class CalculatorV2 implements LoggableCalculator
{
    @Log
    @Override
    public void calculate()
    {}

    @Override
    public void calculate(int param)
    {}

    @Log
    @Override
    public void noLogCalculate(int param)
    {}

    @Override
    public void calculate(int param, int pamPam)
    {}
}

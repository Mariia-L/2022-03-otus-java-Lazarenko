package homework.aop;

public class Calculator implements LoggableCalculator
{
    @Log
    @Override
    public void calculate(int param)
    {}

    @Override
    public void noLogCalculate(int param)
    {}

    @Log
    @Override
    public void calculate(int param, int pamPam)
    {}
}

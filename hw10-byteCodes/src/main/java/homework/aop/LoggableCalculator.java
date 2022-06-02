package homework.aop;

public interface LoggableCalculator
{
    @Log
    void calculate(int param);

    void noLogCalculate(int param);

    @Log
    void calculate(int param, int pamPam);
}
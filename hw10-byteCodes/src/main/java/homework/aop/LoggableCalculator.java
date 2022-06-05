package homework.aop;

public interface LoggableCalculator
{
    void calculate();

    void calculate(int param);

    void noLogCalculate(int param);

    void calculate(int param, int pamPam);
}
package homework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class CalculatorProxy {

    private CalculatorProxy(){}

    static public LoggableCalculator createCalculator()
    {
        InvocationHandler handler = new CalculatorInvocationHandler(new Calculator());
        return (LoggableCalculator) Proxy.newProxyInstance(CalculatorProxy.class.getClassLoader(),
                new Class<?>[]{LoggableCalculator.class}, handler);
    }

    static class CalculatorInvocationHandler implements InvocationHandler
    {
        private final LoggableCalculator calculator;

        CalculatorInvocationHandler(LoggableCalculator calculator)
        {
            this.calculator = calculator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            if (method.isAnnotationPresent(Log.class))
            {
                System.out.println("");
                System.out.print("executed method: " + method.getName());
                Parameter[] parameters = method.getParameters();
                for(int i = 0; i < parameters.length; i++)
                {
                    String paramName = parameters[i].isNamePresent() ? parameters[i].getName() : "param" + i;
                    System.out.print(", " + paramName + ": " + args[i]);
                }
            }

            return method.invoke(calculator, args);
        }
    }
}
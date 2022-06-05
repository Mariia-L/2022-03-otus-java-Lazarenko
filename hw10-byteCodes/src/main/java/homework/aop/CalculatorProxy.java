package homework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorProxy {

    private final LoggableCalculator loggableCalculator;
    private final List<Method> annotatedMethods;

    public CalculatorProxy(LoggableCalculator loggableCalculator)
    {
        this.loggableCalculator = loggableCalculator;
        this.annotatedMethods = Arrays.stream(loggableCalculator.getClass().getMethods())
                .filter(x->x.isAnnotationPresent(Log.class)).collect(Collectors.toList());
    }

    public LoggableCalculator createCalculator()
    {
        InvocationHandler handler = new CalculatorInvocationHandler(loggableCalculator);
        return (LoggableCalculator) Proxy.newProxyInstance(CalculatorProxy.class.getClassLoader(),
                new Class<?>[]{LoggableCalculator.class}, handler);
    }

    class CalculatorInvocationHandler implements InvocationHandler
    {
        private final LoggableCalculator calculator;

        CalculatorInvocationHandler(LoggableCalculator calculator)
        {
            this.calculator = calculator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            if (isAnnotatedMethod(method))
            {
                printMethod(method, args);
            }

            return method.invoke(calculator, args);
        }

        private boolean isAnnotatedMethod(Method method)
        {
            for(Method annotatedMethod : annotatedMethods)
            {
                if (method.getName().equals(annotatedMethod.getName())
                    && isMethodParamsEqual(method, annotatedMethod))
                        return true;
            }

            return false;
        }

        private boolean isMethodParamsEqual(Method method, Method annotatedMethod)
        {
            if (method.getParameterCount() != annotatedMethod.getParameterCount())
            {
                return false;
            }
            else if (method.getParameterCount() == 0)
            {
                return true;
            }

            boolean isParametersEqual = false;
            for (int i = 0; i < method.getParameterCount(); i++)
            {
                isParametersEqual = annotatedMethod.getParameterTypes()[i].equals(method.getParameterTypes()[i]);
            }

            return isParametersEqual;
        }

        private void printMethod(Method method, Object[] args)
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
    }
}
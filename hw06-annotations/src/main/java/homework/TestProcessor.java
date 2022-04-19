package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestProcessor {

    private static final TestProcessor INSTANCE = new TestProcessor();

    private TestProcessor() {}

    public static TestProcessor getInstance()
    {
        return INSTANCE;
    }

    public void runTest(String className)
    {
        try
        {
            run(className);
        }
        catch (Exception exception)
        {
            printResult(0, 0);
        }
    }

    private void run(String className) throws Exception
    {
        int successCounter = 0;
        int errorCounter = 0;

        Class<?> clazz = Class.forName(className);

        Method[] methods = clazz.getDeclaredMethods();

        Method[] testMethods = Arrays.stream(methods).filter(m -> m.getAnnotation(Test.class) != null).toArray(Method[]::new);
        Method[] beforeMethods = Arrays.stream(methods).filter(m -> m.getAnnotation(Before.class) != null).toArray(Method[]::new);
        Method[] afterMethods = Arrays.stream(methods).filter(m -> m.getAnnotation(After.class) != null).toArray(Method[]::new);

        Constructor<?> constructor = clazz.getConstructor();

        for (Method method : testMethods)
        {
            Object object = constructor.newInstance();
            if (runMethods(object, beforeMethods)
                    && runTestMethod(object, method)
                    && runMethods(object, afterMethods))
            {
                successCounter++;
            }
            else
            {
                errorCounter++;
            }
        }

        printResult(successCounter, errorCounter);
    }

    private static boolean runMethods(Object object, Method[] methods) {

        for (Method method : methods)
        {
            try
            {
                method.setAccessible(true);
                method.invoke(object);
            }
            catch (Exception exception)
            {
                return false;
            }
        }
        return true;
    }

    private static boolean runTestMethod(Object object, Method method)
    {
        try
        {
            method.setAccessible(true);
            Class<?>[] methodClasses = method.getParameterTypes();
            Object[] parameters = new Object[methodClasses.length];
            for (int i = 0; i< methodClasses.length; i++)
            {
                parameters[i] = methodClasses[i].getConstructor().newInstance();
            }
            method.invoke(object, parameters);
        }
        catch (Exception exception)
        {
            return false;
        }

        return true;
    }

    private static void printResult(int successCounter, int errorCounter)
    {
        int resultCounter = successCounter + errorCounter;
        System.out.println("Всего запущено тестов: " + resultCounter);
        System.out.println("Выполнено тестов: " + successCounter);
        System.out.println("Упало тестов: " + errorCounter);
    }
}
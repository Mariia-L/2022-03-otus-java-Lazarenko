package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.util.Random;

public class TestClass {

    @Before
    public void methodBefore()
    {
        System.out.println("Before");
    }

    @Before
    public void methodBeforeException() throws Exception
    {
        int random = new Random().nextInt();
        if (random % 10 == 0)
        {
            System.out.println("Exception before");
            throw new Exception();
        }
        System.out.println("Before without exception");
    }

    @Test
    public void methodTestOne()
    {
        System.out.println("Test one");
    }

    @Test
    public void methodTestException() throws Exception
    {
        System.out.println("Test exception");
        throw new Exception("Ups...");
    }

    @Test
    private void methodPrivate()
    {
        System.out.println("Private method");
    }

    @Test
    public String methodWithParams(String string)
    {
        System.out.println("Method with param " + string);
        return string;
    }

    @Test
    public void methodTestTwo()
    {
        System.out.println("Test two");
    }

    @After
    public void methodAfter()
    {
        System.out.println("After");
    }

    @After
    public void methodAfterException() throws Exception
    {
        int random = new Random().nextInt();
        if (random % 10 == 0)
        {
            System.out.println("Exception after");
            throw new Exception();
        }
        System.out.println("After without exception");
    }
}
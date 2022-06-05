package solid.processor;

import org.junit.Assert;
import org.junit.Test;
import solid.money.MoneyCells;

public class ATMMoneyProcessorTest
{
    @Test
    public void testInputMoney()
    {
        MoneyCells moneyCells = new MoneyCells().addHundred(1).addThousand(1);
        MoneyCells inputMoneyCells = new MoneyCells().addFiveHundreds(1).addFiveThousands(1);

        ATMMoneyProcessor.inputMoney(moneyCells, inputMoneyCells);

        Assert.assertEquals(4, moneyCells.getCellsMap().values().stream().filter(value -> value == 1).count());
    }

    @Test
    public void testGetBalance()
    {
        MoneyCells moneyCells = new MoneyCells().addHundred(1).addThousand(1).addFiveThousands(1).addFiveHundreds(1);

        Assert.assertEquals(Integer.valueOf(6600), ATMMoneyProcessor.getBalance(moneyCells));
    }

    @Test(expected = NoBanknotesException.class)
    public void testWithdrawMoneyException() throws NoBanknotesException
    {
        MoneyCells moneyCells = new MoneyCells().addHundred(1).addThousand(1).addFiveThousands(1).addFiveHundreds(1);

        ATMMoneyProcessor.withdrawMoney(700, moneyCells);
    }

    @Test
    public void testWithdrawMoneySuccess() throws NoBanknotesException {
        MoneyCells moneyCells = new MoneyCells().addHundred(2).addFiveHundreds(1);

        ATMMoneyProcessor.withdrawMoney(700, moneyCells);
    }
}
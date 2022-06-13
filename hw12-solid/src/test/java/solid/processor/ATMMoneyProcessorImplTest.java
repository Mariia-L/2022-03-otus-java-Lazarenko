package solid.processor;

import org.junit.Assert;
import org.junit.Test;
import solid.money.MoneyCellDenomination;
import solid.money.MoneyCells;
import solid.money.MoneyCellsImpl;

public class ATMMoneyProcessorImplTest
{
    @Test
    public void testInputMoney()
    {
        MoneyCells moneyCells = new MoneyCellsImpl();
        moneyCells.addOneValue(MoneyCellDenomination.HUNDRED);
        moneyCells.addOneValue(MoneyCellDenomination.THOUSAND);

        MoneyCells inputMoneyCells = new MoneyCellsImpl();
        inputMoneyCells.addOneValue(MoneyCellDenomination.FIVE_HUNDREDS);
        inputMoneyCells.addOneValue(MoneyCellDenomination.FIVE_THOUSANDS);

        new ATMMoneyProcessorImpl().inputMoney(moneyCells, inputMoneyCells);

        Assert.assertEquals(4, moneyCells.getCellsMap().values().stream().filter(value -> value == 1).count());
    }

    @Test
    public void testGetBalance()
    {
        MoneyCells moneyCells = new MoneyCellsImpl();

        moneyCells.addOneValue(MoneyCellDenomination.HUNDRED);
        moneyCells.addOneValue(MoneyCellDenomination.THOUSAND);
        moneyCells.addOneValue(MoneyCellDenomination.FIVE_HUNDREDS);
        moneyCells.addOneValue(MoneyCellDenomination.FIVE_THOUSANDS);

        Assert.assertEquals(Integer.valueOf(6600), new ATMMoneyProcessorImpl().getBalance(moneyCells));
    }

    @Test(expected = NoBanknotesException.class)
    public void testWithdrawMoneyException() throws NoBanknotesException
    {
        MoneyCellsImpl moneyCells = new MoneyCellsImpl();

        moneyCells.addOneValue(MoneyCellDenomination.HUNDRED);
        moneyCells.addOneValue(MoneyCellDenomination.THOUSAND);
        moneyCells.addOneValue(MoneyCellDenomination.FIVE_HUNDREDS);
        moneyCells.addOneValue(MoneyCellDenomination.FIVE_THOUSANDS);

        new ATMMoneyProcessorImpl().withdrawMoney(700, moneyCells);
    }

    @Test
    public void testWithdrawMoneySuccess() throws NoBanknotesException {
        MoneyCellsImpl moneyCells = new MoneyCellsImpl();

        moneyCells.addOneValue(MoneyCellDenomination.HUNDRED);
        moneyCells.addOneValue(MoneyCellDenomination.HUNDRED);
        moneyCells.addOneValue(MoneyCellDenomination.FIVE_HUNDREDS);

        new ATMMoneyProcessorImpl().withdrawMoney(700, moneyCells);
    }
}
package solid.money;

import org.junit.Assert;
import org.junit.Test;

import static solid.money.MoneyCellDenomination.HUNDRED;

public class MoneyCellsImplTest
{
    @Test
    public void testDefaultConstructor()
    {
        MoneyCellsImpl moneyCells = new MoneyCellsImpl();
        Assert.assertNotNull(moneyCells.getCellsMap());
    }

    @Test
    public void testAddNewCell()
    {
        MoneyCell moneyCell = new MoneyCell(HUNDRED, 1);
        MoneyCellsImpl moneyCells = new MoneyCellsImpl().addNewCell(moneyCell);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(HUNDRED));
    }

    @Test
    public void testAddOneValue()
    {
        MoneyCellsImpl moneyCells = new MoneyCellsImpl();
        moneyCells.addOneValue(HUNDRED);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(HUNDRED));
    }
}
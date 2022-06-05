package solid.money;

import org.junit.Assert;
import org.junit.Test;

public class MoneyCellsTest
{
    @Test
    public void testDefaultConstructor()
    {
        MoneyCells moneyCells = new MoneyCells();
        Assert.assertNotNull(moneyCells.getCellsMap());
        Assert.assertEquals(0, moneyCells.getCellsMap().values().stream().filter(value -> value != 0).count());
    }

    @Test
    public void testAddHundred()
    {
        MoneyCells moneyCells = new MoneyCells().addHundred(1);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(MoneyCells.HUNDRED));
    }

    @Test
    public void testAddFiveHundreds()
    {
        MoneyCells moneyCells = new MoneyCells().addFiveHundreds(1);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(MoneyCells.FIVE_HUNDREDS));
    }

    @Test
    public void testAddThousand()
    {
        MoneyCells moneyCells = new MoneyCells().addThousand(1);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(MoneyCells.THOUSAND));
    }

    @Test
    public void testAddFiveThousands()
    {
        MoneyCells moneyCells = new MoneyCells().addFiveThousands(1);
        Assert.assertEquals(Integer.valueOf(1), moneyCells.getCellsMap().get(MoneyCells.FIVE_THOUSANDS));
    }

    @Test
    public void testEmptyAddSomeAmount()
    {
        MoneyCells moneyCells = new MoneyCells();
        moneyCells.addSomeMoney(MoneyCells.HUNDRED+1);
        Assert.assertEquals(0, moneyCells.getCellsMap().values().stream().filter(value -> value != 0).count());
    }

    @Test
    public void testAddSomeAmount()
    {
        MoneyCells moneyCells = new MoneyCells();
        moneyCells.addSomeMoney(MoneyCells.HUNDRED);
        moneyCells.addSomeMoney(MoneyCells.THOUSAND);
        moneyCells.addSomeMoney(MoneyCells.FIVE_HUNDREDS);
        moneyCells.addSomeMoney(MoneyCells.FIVE_THOUSANDS);
        Assert.assertEquals(0, moneyCells.getCellsMap().values().stream().filter(value -> value == 0).count());
    }
}
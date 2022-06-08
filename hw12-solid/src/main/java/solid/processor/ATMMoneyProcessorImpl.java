package solid.processor;

import solid.money.MoneyCellType;
import solid.money.MoneyCells;
import solid.money.MoneyCellsImpl;

import java.util.Map;

public class ATMMoneyProcessorImpl implements ATMMoneyProcessor
{
    @Override
    public MoneyCells withdrawMoney(int amount, MoneyCells moneyCells) throws NoBanknotesException
    {
        MoneyCells withdrawnMoneyCells = new MoneyCellsImpl();
        int newAmount = amount;

        for (Map.Entry<MoneyCellType, Integer> entry : moneyCells.getCellsMap().entrySet())
        {
            if (newAmount >= entry.getKey().getValue())
            {
                int key = entry.getKey().getValue();
                int value = newAmount / key;
                if (entry.getValue() >= value)
                {
                    for (int i = 0; i < value; i++)
                    {
                        withdrawnMoneyCells.addOneValue(entry.getKey());
                        entry.setValue(entry.getValue() - 1);
                        newAmount -= key;
                    }
                }
            }
        }

        if (newAmount != 0)
            throw new NoBanknotesException();

        return withdrawnMoneyCells;
    }

    @Override
    public void inputMoney(MoneyCells moneyCells, MoneyCells inputMoneyCells)
    {
        inputMoneyCells.getCellsMap().forEach((k, v) -> moneyCells.getCellsMap().merge(k, v, Integer::sum));
    }

    @Override
    public Integer getBalance(MoneyCells moneyCells)
    {
        return moneyCells.getCellsMap().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getValue()*entry.getValue()).sum();
    }
}

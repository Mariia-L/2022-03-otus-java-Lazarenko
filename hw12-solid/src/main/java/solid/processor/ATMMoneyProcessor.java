package solid.processor;

import lombok.experimental.UtilityClass;
import solid.money.MoneyCells;

import java.util.Map;

@UtilityClass
public class ATMMoneyProcessor
{
    public MoneyCells withdrawMoney(int amount, MoneyCells moneyCells) throws NoBanknotesException
    {
        MoneyCells withdrawnMoneyCells = new MoneyCells();
        int newAmount = amount;

        for (Map.Entry<Integer, Integer> entry : moneyCells.getCellsMap().entrySet())
        {
            if (newAmount >= entry.getKey())
            {
                int key = entry.getKey();
                int value = newAmount / key;
                if (entry.getValue() >= value)
                {
                    for (int i = 0; i < value; i++)
                    {
                        withdrawnMoneyCells.addSomeMoney(entry.getKey());
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

    public void inputMoney(MoneyCells moneyCells, MoneyCells inputMoneyCells)
    {
        inputMoneyCells.getCellsMap().forEach((k, v) -> moneyCells.getCellsMap().merge(k, v, Integer::sum));
    }

    public Integer getBalance(MoneyCells moneyCells)
    {
        return moneyCells.getCellsMap().entrySet().stream().mapToInt(entry -> entry.getKey()*entry.getValue()).sum();
    }
}

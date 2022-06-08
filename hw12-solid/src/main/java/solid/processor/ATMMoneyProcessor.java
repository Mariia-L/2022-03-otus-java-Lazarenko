package solid.processor;

import solid.money.MoneyCells;

public interface ATMMoneyProcessor
{
    MoneyCells withdrawMoney(int amount, MoneyCells moneyCells) throws NoBanknotesException;
    void inputMoney(MoneyCells moneyCells, MoneyCells inputMoneyCells);

    Integer getBalance(MoneyCells moneyCells);
}

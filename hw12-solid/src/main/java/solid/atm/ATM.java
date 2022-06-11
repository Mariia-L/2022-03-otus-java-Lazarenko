package solid.atm;

import solid.money.MoneyCellsImpl;

public interface ATM
{
    void withdrawMoney(int amount);

    void inputMoney(MoneyCellsImpl inputMoneyCells);

    void printBalance();

    void printExtendedBalance();
}

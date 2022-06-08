package solid.printer;

import solid.money.MoneyCellType;

import java.util.Map;

public interface ATMBalancePrinter
{
    void printBalance(int balance);

    void printExtendedBalance(Map<MoneyCellType, Integer> balanceMap);

    void printNoAvailableBanknotesError(Map<MoneyCellType, Integer> balanceMap);

    void printWithdrawnMoney(Map<MoneyCellType, Integer> balanceMap);
}

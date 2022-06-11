package solid.printer;

import solid.money.MoneyCellDenomination;

import java.util.Map;

public interface ATMBalancePrinter
{
    void printBalance(int balance);

    void printExtendedBalance(Map<MoneyCellDenomination, Integer> balanceMap);

    void printNoAvailableBanknotesError(Map<MoneyCellDenomination, Integer> balanceMap);

    void printWithdrawnMoney(Map<MoneyCellDenomination, Integer> balanceMap);
}

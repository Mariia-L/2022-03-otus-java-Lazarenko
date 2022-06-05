package solid.printer;

import java.util.Map;

public interface ATMBalancePrinter
{
    void printBalance(int balance);

    void printExtendedBalance(Map<Integer, Integer> balanceMap);

    void printNoAvailableBanknotesError(Map<Integer, Integer> balanceMap);

    void printWithdrawnMoney(Map<Integer, Integer> balanceMap);
}

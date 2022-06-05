package solid.printer;

import java.util.Map;

public class ATMBalancePrinterImpl implements ATMBalancePrinter
{
    @Override
    public void printBalance(int balance)
    {
        System.out.println("--------");
        System.out.println("Общий остаток: " + balance + " рублей");
        System.out.println("--------");
    }

    @Override
    public void printExtendedBalance(Map<Integer, Integer> balanceMap)
    {
        System.out.println("--------");
        balanceMap.entrySet().stream().filter(entry->entry.getValue() > 0)
                .forEach(entry -> System.out.println("Номинал: " + entry.getKey() + " Количество купюр: "
                        + entry.getValue() + " Сумма: " + entry.getValue()*entry.getKey()));
        System.out.println("--------");
    }

    @Override
    public void printNoAvailableBanknotesError(Map<Integer, Integer> balanceMap)
    {
        System.out.println("--------");
        System.out.println("Нет подходящих банкнот");
        printExtendedBalance(balanceMap);
        System.out.println("--------");
    }

    @Override
    public void printWithdrawnMoney(Map<Integer, Integer> balanceMap)
    {
        System.out.println("--------");
        System.out.println("Выдано: ");
        printExtendedBalance(balanceMap);
        System.out.println("--------");
    }
}

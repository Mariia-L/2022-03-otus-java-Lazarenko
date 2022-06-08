package solid.printer;

import solid.money.MoneyCellType;

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
    public void printExtendedBalance(Map<MoneyCellType, Integer> balanceMap)
    {
        System.out.println("--------");
        balanceMap.entrySet().stream().filter(entry->entry.getValue() > 0)
                .forEach(entry -> System.out.println("Номинал: " + entry.getKey().getValue() + " Количество купюр: "
                        + entry.getValue() + " Сумма: " + entry.getValue()*entry.getKey().getValue()));
        System.out.println("--------");
    }

    @Override
    public void printNoAvailableBanknotesError(Map<MoneyCellType, Integer> balanceMap)
    {
        System.out.println("--------");
        System.out.println("Нет подходящих банкнот");
        printExtendedBalance(balanceMap);
        System.out.println("--------");
    }

    @Override
    public void printWithdrawnMoney(Map<MoneyCellType, Integer> balanceMap)
    {
        System.out.println("--------");
        System.out.println("Выдано: ");
        printExtendedBalance(balanceMap);
        System.out.println("--------");
    }
}

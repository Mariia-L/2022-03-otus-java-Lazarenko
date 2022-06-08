package solid;

import solid.money.MoneyCell;
import solid.money.MoneyCells;
import solid.money.MoneyCellsImpl;
import solid.atm.ATM;
import solid.printer.ATMBalancePrinterImpl;
import solid.processor.ATMMoneyProcessorImpl;

import static solid.money.MoneyCellType.*;

public class ATMDemo
{
    public static void main(String[] args)
    {
        MoneyCells moneyCells = new MoneyCellsImpl()
                .addNewCell(new MoneyCell(HUNDRED, 5))
                .addNewCell(new MoneyCell(FIVE_HUNDREDS, 5))
                .addNewCell(new MoneyCell(THOUSAND, 5))
                .addNewCell(new MoneyCell(FIVE_THOUSANDS, 5));

        ATM atm = new ATM(moneyCells, new ATMMoneyProcessorImpl(), new ATMBalancePrinterImpl());
        atm.printExtendedBalance();
        atm.printBalance();

        atm.withdrawMoney(6000);
        atm.withdrawMoney(3900);

        atm.printExtendedBalance();
        atm.printBalance();
    }
}

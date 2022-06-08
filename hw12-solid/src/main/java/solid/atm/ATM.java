package solid.atm;

import solid.money.MoneyCells;
import solid.money.MoneyCellsImpl;
import solid.processor.ATMMoneyProcessor;
import solid.printer.ATMBalancePrinter;
import solid.processor.NoBanknotesException;

public class ATM
{
    private final MoneyCells moneyCells;
    private final ATMBalancePrinter balancePrinter;
    private final ATMMoneyProcessor moneyProcessor;

    public ATM(MoneyCells moneyCells, ATMMoneyProcessor moneyProcessor, ATMBalancePrinter balancePrinter)
    {
        this.moneyCells = moneyCells;
        this.balancePrinter = balancePrinter;
        this.moneyProcessor = moneyProcessor;
    }

    public void withdrawMoney(int amount)
    {
        MoneyCells withdrawnMoneyCells;

        try
        {
            withdrawnMoneyCells = moneyProcessor.withdrawMoney(amount, moneyCells);
            balancePrinter.printWithdrawnMoney(withdrawnMoneyCells.getCellsMap());
        }
        catch (NoBanknotesException exception)
        {
            System.out.println(exception.getMessage());
            balancePrinter.printNoAvailableBanknotesError(moneyCells.getCellsMap());
        }
    }

    public void inputMoney(MoneyCellsImpl inputMoneyCells)
    {
       moneyProcessor.inputMoney(moneyCells, inputMoneyCells);
    }

    public void printBalance()
    {
        balancePrinter.printBalance(moneyProcessor.getBalance(moneyCells));
    }

    public void printExtendedBalance()
    {
        balancePrinter.printExtendedBalance(moneyCells.getCellsMap());
    }
}

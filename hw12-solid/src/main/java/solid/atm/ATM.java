package solid.atm;

import solid.money.MoneyCells;
import solid.processor.ATMMoneyProcessor;
import solid.printer.ATMBalancePrinter;
import solid.processor.NoBanknotesException;

public class ATM
{
    private final MoneyCells moneyCells;
    private final ATMBalancePrinter balancePrinter;

    public ATM(ATMBalancePrinter balancePrinter)
    {
        this.moneyCells = new MoneyCells();
        this.balancePrinter = balancePrinter;
    }

    public void withdrawMoney(int amount)
    {
        MoneyCells withdrawnMoneyCells;

        try
        {
            withdrawnMoneyCells = ATMMoneyProcessor.withdrawMoney(amount, moneyCells);
            balancePrinter.printWithdrawnMoney(withdrawnMoneyCells.getCellsMap());
        }
        catch (NoBanknotesException exception)
        {
            System.out.println(exception.getMessage());
            balancePrinter.printNoAvailableBanknotesError(moneyCells.getCellsMap());
        }
    }

    public void inputMoney(MoneyCells inputMoneyCells)
    {
       ATMMoneyProcessor.inputMoney(moneyCells, inputMoneyCells);
    }

    public void printBalance()
    {
        balancePrinter.printBalance(ATMMoneyProcessor.getBalance(moneyCells));
    }

    public void printExtendedBalance()
    {
        balancePrinter.printExtendedBalance(moneyCells.getCellsMap());
    }
}

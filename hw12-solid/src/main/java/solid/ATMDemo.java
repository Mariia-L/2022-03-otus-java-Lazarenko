package solid;

import solid.money.MoneyCells;
import solid.atm.ATM;
import solid.printer.ATMBalancePrinterImpl;

public class ATMDemo
{
    public static void main(String[] args) {
        ATM atm = new ATM(new ATMBalancePrinterImpl());
        atm.inputMoney(new MoneyCells().addHundred(5)
                .addFiveHundreds(5).addThousand(5).addFiveThousands(5));
        atm.printExtendedBalance();
        atm.printBalance();

        atm.withdrawMoney(6000);
        atm.withdrawMoney(3900);

        atm.printExtendedBalance();
        atm.printBalance();
    }
}

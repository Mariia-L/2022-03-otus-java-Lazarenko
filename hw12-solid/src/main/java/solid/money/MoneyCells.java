package solid.money;

import java.util.Map;

public interface MoneyCells
{
    Map<MoneyCellDenomination, Integer> getCellsMap();

    MoneyCells addNewCell(MoneyCell moneyCell);

    void addOneValue(MoneyCellDenomination value);
}

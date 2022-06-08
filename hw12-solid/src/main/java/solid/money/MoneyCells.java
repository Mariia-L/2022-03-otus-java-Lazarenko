package solid.money;

import java.util.Map;

public interface MoneyCells
{
    Map<MoneyCellType, Integer> getCellsMap();

    MoneyCells addNewCell(MoneyCell moneyCell);

    void addOneValue(MoneyCellType value);
}

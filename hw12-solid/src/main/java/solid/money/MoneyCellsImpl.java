package solid.money;

import lombok.Getter;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Getter
public class MoneyCellsImpl implements MoneyCells
{
    private final Map<MoneyCellType, Integer> cellsMap;

    /**
     * DESC
     */
    public MoneyCellsImpl()
    {
        cellsMap = new TreeMap<>(new Comparator<MoneyCellType>() {
            @Override
            public int compare(MoneyCellType o1, MoneyCellType o2) {
                return o2.getValue()-o1.getValue();
            }
        });
    }

    public MoneyCellsImpl addNewCell(MoneyCell moneyCell)
    {
        cellsMap.put(moneyCell.getMoneyCellType(), moneyCell.getNumber());
        return this;
    }

    public void addOneValue(MoneyCellType value)
    {
        Integer number = cellsMap.containsKey(value) ? cellsMap.get(value) + 1 : 1;
        cellsMap.put(value, number);
    }
}

package solid.money;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class MoneyCells
{
    public static final int HUNDRED = 100;
    public static final int FIVE_HUNDREDS = 500;
    public static final int THOUSAND = 1000;
    public static final int FIVE_THOUSANDS = 5000;

    private final Map<Integer, Integer> cellsMap;

    /**
     * DESC
     */
    public MoneyCells()
    {
        cellsMap = new LinkedHashMap<>();
        cellsMap.put(FIVE_THOUSANDS, 0);
        cellsMap.put(THOUSAND, 0);
        cellsMap.put(FIVE_HUNDREDS, 0);
        cellsMap.put(HUNDRED, 0);
    }

    public void addSomeMoney(Integer amount)
    {
        switch (amount)
        {
            case HUNDRED -> cellsMap.put(HUNDRED, cellsMap.get(HUNDRED) + 1);
            case FIVE_HUNDREDS -> cellsMap.put(FIVE_HUNDREDS, cellsMap.get(FIVE_HUNDREDS) + 1);
            case THOUSAND -> cellsMap.put(THOUSAND, cellsMap.get(THOUSAND) + 1);
            case FIVE_THOUSANDS -> cellsMap.put(FIVE_THOUSANDS, cellsMap.get(FIVE_THOUSANDS) + 1);
        }
    }

    public MoneyCells addHundred(Integer number)
    {
        cellsMap.put(HUNDRED, cellsMap.get(HUNDRED) + number);
        return this;
    }

    public MoneyCells addFiveHundreds(Integer number)
    {
        cellsMap.put(FIVE_HUNDREDS, cellsMap.get(FIVE_HUNDREDS) + number);
        return this;
    }
    public MoneyCells addThousand(Integer number)
    {
        cellsMap.put(THOUSAND, cellsMap.get(THOUSAND) + number);
        return this;
    }
    public MoneyCells addFiveThousands(Integer number)
    {
        cellsMap.put(FIVE_THOUSANDS, cellsMap.get(FIVE_THOUSANDS) + number);
        return this;
    }
}

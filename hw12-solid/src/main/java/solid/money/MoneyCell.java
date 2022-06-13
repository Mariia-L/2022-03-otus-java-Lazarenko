package solid.money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyCell
{
    private final MoneyCellDenomination moneyCellType;
    private final Integer capacity;
}

package solid.money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyCell
{
    private final MoneyCellType moneyCellType;
    private final Integer number;
}

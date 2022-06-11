package solid.money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MoneyCellDenomination
{
    HUNDRED (100),
    FIVE_HUNDREDS (500),
    THOUSAND (1000),
    FIVE_THOUSANDS (5000);

    private final Integer value;
}

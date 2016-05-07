package net.bugorfeature.basket.model;

import java.math.BigDecimal;

/**
 * Price strategy that simply multiplies the number of items by the price
 *
 * @author Andy Geach
 */
public class PerItem implements PriceStrategy {

    @Override
    public BigDecimal calcTotalForItem(int count, BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(count));
    }
}

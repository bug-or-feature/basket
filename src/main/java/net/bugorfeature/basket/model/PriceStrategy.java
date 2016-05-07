package net.bugorfeature.basket.model;

import java.math.BigDecimal;

/**
 * Strategy for calculating prices
 *
 * @author Andy Geach
 */
public interface PriceStrategy {

    public BigDecimal calcTotalForItem(int count, BigDecimal price);
}

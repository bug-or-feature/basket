package net.bugorfeature.basket.service;

import java.math.BigDecimal;
import java.util.Collection;

import net.bugorfeature.basket.model.ShoppingItem;

/**
 * Service for calculating the basket total price
 *
 * @author Andy Geach
 */
public interface PricingService {


    public BigDecimal getTotal(Collection<ShoppingItem> items);


}

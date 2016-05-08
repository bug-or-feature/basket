package net.bugorfeature.basket.service;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import net.bugorfeature.basket.model.ShoppingItem;

/**
 * Default pricing service
 *
 * @author Andy Geach
 */
public class DefaultPricingService implements PricingService {

    @Autowired
    private ConfigService configService;

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public BigDecimal getTotal(Collection<ShoppingItem> items) {
        BigDecimal total = new BigDecimal("0.00");
        for (ShoppingItem item : items) {
            BigDecimal price = configService.getPriceForItem(item);
            total = total.add(price);
        }

        return total;
    }
}

package net.bugorfeature.basket.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.bugorfeature.basket.model.ShoppingItem;


/**
 * Default static implementation of config service
 *
 * @author Andy Geach
 */
public class StaticConfigService implements ConfigService {

    private Map<ShoppingItem, ShoppingItemConfig> config = new HashMap<>();


    @Override
    public BigDecimal getPriceForItem(ShoppingItem item) {

        ShoppingItemConfig configItem = config.get(item);

        if (configItem == null) {
            throw new IllegalArgumentException("Cannot find item: " + item);
        }

        return configItem.getPrice();
    }

    @Override
    public int getMinimumForItem(ShoppingItem item) {
        ShoppingItemConfig configItem = config.get(item);

        if (configItem == null) {
            throw new IllegalArgumentException("Cannot find item: " + item);
        }

        return configItem.getMinCount();
    }

    @Override
    public Collection<ShoppingItem> itemList() {
        return config.keySet();
    }

    public void setConfigForItem(ShoppingItem item, BigDecimal price, int minimum) {
        ShoppingItemConfig sig = new ShoppingItemConfig();
        sig.setPrice(price);
        sig.setMinCount(minimum);
        config.put(item, sig);
    }


    public void buildDefault() {
        for (ShoppingItem item : ShoppingItem.values()) {
            ShoppingItemConfig sig = new ShoppingItemConfig();
            sig.setPrice(new BigDecimal("0.10").multiply(new BigDecimal(Integer.toString(item.ordinal() + 1)))); // just dummy data
            sig.setMinCount(1);
            config.put(item, sig);
        }
    }


    public static class ShoppingItemConfig {

        private int minCount;

        private BigDecimal price;

        public int getMinCount() {
            return minCount;
        }

        public void setMinCount(int minCount) {
            this.minCount = minCount;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}

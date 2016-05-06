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

    public StaticConfigService() {
        buildDefault();
    }

    @Override
    public BigDecimal getPriceForItem(ShoppingItem item) {

        ShoppingItemConfig configItem = config.get(item);

        if (configItem == null) {
            throw new IllegalArgumentException("Cannot find blah: " + item);
        }

        return configItem.getPrice();
    }

    @Override
    public int getMinimumForItem(ShoppingItem item) {
        return config.get(item).getMinCount();
    }

    @Override
    public Collection<ShoppingItem> itemList() {
        return config.keySet();
    }

    private void buildDefault() {
        for (ShoppingItem item : ShoppingItem.values()) {
            ShoppingItemConfig sig = new ShoppingItemConfig();
            sig.setBasketItem(item);
            sig.setPrice(new BigDecimal("0.10").multiply(new BigDecimal(Integer.toString(item.ordinal() + 1)))); // just dummy data
            sig.setMinCount(1);
            config.put(item, sig);
        }
    }


    public static class ShoppingItemConfig {

        private ShoppingItem basketItem;

        private int minCount;

        private BigDecimal price;

        public ShoppingItem getBasketItem() {
            return basketItem;
        }

        public void setBasketItem(ShoppingItem basketItem) {
            this.basketItem = basketItem;
        }

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

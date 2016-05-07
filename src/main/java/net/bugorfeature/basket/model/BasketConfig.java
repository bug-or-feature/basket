package net.bugorfeature.basket.model;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.EnumMap;
import java.util.Set;

/**
 * Config model for basket app. XML marshalling friendly
 *
 * @author Andy Geach
 */
@XmlRootElement
public class BasketConfig {

    @XmlElementWrapper(name="products")
    protected EnumMap<ShoppingItem, ShoppingItemConfig> configMap = new EnumMap<ShoppingItem, ShoppingItemConfig>(ShoppingItem.class);


    public ShoppingItemConfig put(ShoppingItem key, ShoppingItemConfig value) {
        return configMap.put(key, value);
    }

    public ShoppingItemConfig get(Object key) {
        return configMap.get(key);
    }

    public Set<ShoppingItem> keySet() {
        return configMap.keySet();
    }
}

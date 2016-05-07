package net.bugorfeature.basket.model;

import javax.xml.bind.annotation.XmlType;

import java.math.BigDecimal;

/**
 * Shopping item config
 *
 * @author Andy Geach
 */
@XmlType(propOrder = {"price", "minCount"})
public class ShoppingItemConfig {

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

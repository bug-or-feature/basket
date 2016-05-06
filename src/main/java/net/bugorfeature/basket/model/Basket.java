package net.bugorfeature.basket.model;

import java.util.Collection;

/**
 * Basket of shopping
 *
 * @author Andy Geach
 */
public interface Basket {

    void addItem(ShoppingItem item);

    Collection<ShoppingItem> getItems();

}

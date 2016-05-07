package net.bugorfeature.basket.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple list based implementation of Basket
 *
 * @author Andy Geach
 */
public class BasketImpl implements Basket {

    private List<ShoppingItem> shoppingItems = new ArrayList<>();

    @Override
    public void addItem(ShoppingItem item) {
        shoppingItems.add(item);
    }

    @Override
    public Collection<ShoppingItem> getItems() {
        return shoppingItems;
    }

    @Override
    public Collection<ShoppingItem> getItemsOfType(ShoppingItem item) {
        return shoppingItems.stream()
            .filter(p -> p.equals(item)).collect(Collectors.toList());
    }
}

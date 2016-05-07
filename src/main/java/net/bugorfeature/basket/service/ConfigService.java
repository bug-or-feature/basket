package net.bugorfeature.basket.service;

import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Collection;

import net.bugorfeature.basket.model.ShoppingItem;

/**
 * Service that supplies the application config. shopping items, prices, minimum counts etc
 *
 * @author Andy Geach
 */
public interface ConfigService {

    BigDecimal getPriceForItem(ShoppingItem item);

    int getMinimumForItem(ShoppingItem item);

    Collection<ShoppingItem> itemList();

    void read(Reader input) throws Exception;

    void write(Writer ouput) throws Exception;

}

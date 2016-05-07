package net.bugorfeature.basket.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Collection;

import net.bugorfeature.basket.model.BasketConfig;
import net.bugorfeature.basket.model.ShoppingItem;
import net.bugorfeature.basket.model.ShoppingItemConfig;


/**
 * Default static implementation of config service
 *
 * @author Andy Geach
 */
public class DefaultConfigService implements ConfigService {


    private BasketConfig config = new BasketConfig();


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

    @Override
    public void read(Reader input) {
        try {
            JAXBContext jc = JAXBContext.newInstance(BasketConfig.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            config = (BasketConfig) unmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void write(Writer output) {
        try {
            JAXBContext jc = JAXBContext.newInstance(BasketConfig.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(config, output);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
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
}

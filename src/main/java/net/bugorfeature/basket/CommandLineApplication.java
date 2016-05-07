package net.bugorfeature.basket;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bugorfeature.basket.model.Basket;
import net.bugorfeature.basket.model.BasketImpl;
import net.bugorfeature.basket.model.ShoppingItem;
import net.bugorfeature.basket.service.ConfigService;
import net.bugorfeature.basket.service.DefaultPricingService;
import net.bugorfeature.basket.service.PricingService;
import net.bugorfeature.basket.service.DefaultConfigService;

/**
 * Basket application
 *
 * @author Andy Geach
 */
public class CommandLineApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineApplication.class);

    private ConfigService configService;

    private PricingService pricingService;

    private Basket basket = new BasketImpl();

    public ConfigService getConfigService() {
        return configService;
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public PricingService getPricingService() {
        return pricingService;
    }

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public static void main(String[] args) {

        CommandLineApplication app = new CommandLineApplication();
        app.setupDefaultServices();
        app.runMinimalAppWithDefaultConfig();
    }

    protected void setupDefaultServices() {

        basket = new BasketImpl();

        configService = new DefaultConfigService();
        ((DefaultConfigService)configService).buildDefault();
        setConfigService(configService);

        pricingService = new DefaultPricingService();
        ((DefaultPricingService)pricingService).setConfigService(configService);
        setPricingService(pricingService);
    }

    protected void runMinimalAppWithDefaultConfig() {
        for (ShoppingItem item : configService.itemList()) {
            while (basket.getItemsOfType(item).size() < configService.getMinimumForItem(item)) {
                basket.addItem(item);
            }
        }
        BigDecimal total = pricingService.getTotal(basket.getItems());
        LOGGER.info("Basket total: " + total);
    }

    protected BigDecimal getTotal() {
        return pricingService.getTotal(basket.getItems());
    }
}

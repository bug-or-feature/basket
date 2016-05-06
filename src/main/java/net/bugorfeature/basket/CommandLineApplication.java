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
import net.bugorfeature.basket.service.StaticConfigService;

/**
 * Basket application
 *
 * @author Andy Geach
 */
public class CommandLineApplication {

    private static final Logger log = LoggerFactory.getLogger(CommandLineApplication.class);

    private ConfigService configService;

    private PricingService pricingService;

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

        ConfigService configService = new StaticConfigService();
        app.setConfigService(configService);

        DefaultPricingService pricingService = new DefaultPricingService();
        pricingService.setConfigService(configService);
        app.setPricingService(pricingService);

        app.runMinimalAppWithDefaultConfig();
    }

    private void runMinimalAppWithDefaultConfig() {
        Basket basket = new BasketImpl();
        for (ShoppingItem item : configService.itemList()) {
            for (int i = 0; i < configService.getMinimumForItem(item); i++) {
                basket.addItem(item);
            }
        }
        BigDecimal total = pricingService.getTotal(basket.getItems());
        log.info("Basket total: " + total);
    }
}

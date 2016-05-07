package net.bugorfeature.basket;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for application
 *
 * @author Andy Geach
 */
public class CommandLineApplicationITCase {

    private String configFile;

    @Before
    public void setup() {
        try {
            configFile = new File("src/main/resources/config.xml").getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Problem reading config file: " + e);
        }
    }

    @Test
    public void testDefaultConfig() {
        CommandLineApplication app = new CommandLineApplication();
        app.setupDefaultConfigService();
        app.setupPricingService();
        app.runMinimalAppWithDefaultConfig();

        assertEquals(new BigDecimal("1.50"), app.getTotal());
    }

    @Test
    public void testCustomConfig() {
        CommandLineApplication app = new CommandLineApplication();
        app.setupCustomConfigService(configFile);
        app.setupPricingService();
        app.runMinimalAppWithDefaultConfig();

        assertEquals(new BigDecimal("1.50"), app.getTotal());
    }
}

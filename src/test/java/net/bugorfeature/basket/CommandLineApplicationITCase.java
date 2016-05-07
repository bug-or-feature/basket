package net.bugorfeature.basket;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for application
 *
 * @author Andy Geach
 */
public class CommandLineApplicationITCase {

    @Test
    public void test() {
        CommandLineApplication app = new CommandLineApplication();
        app.setupDefaultServices();
        app.runMinimalAppWithDefaultConfig();

        assertEquals(new BigDecimal("1.50"), app.getTotal());
    }
}

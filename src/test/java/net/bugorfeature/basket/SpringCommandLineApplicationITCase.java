package net.bugorfeature.basket;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Integration test for spring application
 *
 * @author Andy Geach
 */
public class SpringCommandLineApplicationITCase {

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private ConfigurableApplicationContext context;

    @Before
    public void setup() {
        context = new SpringApplication(SpringCommandLineApplication.class).run();
    }

    @After
    public void clean() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testContextLoading() throws Exception {
        SpringCommandLineApplication configuration = context.getBean(SpringCommandLineApplication.class);
        assertNotNull(configuration);
    }
}

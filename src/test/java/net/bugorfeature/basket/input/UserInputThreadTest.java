package net.bugorfeature.basket.input;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import net.bugorfeature.basket.command.BasketCommandRunner;

import static org.mockito.Mockito.mock;

/**
 * Unit test for UserInputThread
 *
 * @author Andy Geach
 */
public class UserInputThreadTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private UserInputThread userInputThread;

    @Before
    public void setup() {
        userInputThread = new UserInputThread();
        BasketCommandRunner runner = mock(BasketCommandRunner.class);
        userInputThread.setRunner(runner);
        userInputThread.setScanner(new Scanner("exit"));
    }

    @Test
    public void assertApplicationExitsOnExitCommand() {
        exit.expectSystemExit();
        userInputThread.run();
    }
}

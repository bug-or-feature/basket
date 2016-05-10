package net.bugorfeature.basket.input;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cli.util.Log;

import net.bugorfeature.basket.command.BasketCommandRunner;

/**
 * Thread for handling user input
 *
 * @author Andy Geach
 */
public class UserInputThread implements Runnable {

    private volatile boolean exitRequested = false;

    private Scanner scanner;

    private int maxLoops = -1; // loop forever

    private int loopCount;

    @Autowired
    private BasketCommandRunner runner;

    @Override
    public void run() {

        while (!exitRequested && !maxLoopsReached()) {

            //  prompt for the user's name
            Log.infoPrint("> ");

            // accept input
            String command = null;
            if (scanner.hasNext()) {
                command = scanner.nextLine();

                int exitCode = runner.runAndHandleErrors(command.split("\\s+"));
                if (exitCode != 0 || "exit".equals(command)) {
                    exitRequested = true;
                    System.exit(exitCode);
                }
            }

            loopCount++;
        }
    }

    public void setRunner(BasketCommandRunner runner) {
        this.runner = runner;
    }

    public void setMaxLoops(int maxLoops) {
        this.maxLoops = maxLoops;
    }

    /**
     * Have we reached the maximum number of loops?
     *
     * We need this because when running in the application, the call to scanner.nextLine() blocks, but does not
     * in the specifications
     *
     * @return true if we have looped the maximum number
     */
    private boolean maxLoopsReached() {
        return maxLoops != -1 && loopCount >= maxLoops;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

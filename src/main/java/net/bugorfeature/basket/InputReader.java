package net.bugorfeature.basket;

import java.io.FileReader;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.cli.util.Log;

import net.bugorfeature.basket.command.AddCommand;
import net.bugorfeature.basket.command.BasketCommandRunner;
import net.bugorfeature.basket.command.TotalCommand;
import net.bugorfeature.basket.service.ConfigService;
import net.bugorfeature.basket.service.DefaultConfigService;

/**
 * Does the work of accepting command line input
 *
 * @author Andy Geach
 */
public class InputReader implements CommandLineRunner {

    @Autowired
    private TotalCommand totalCommand;

    @Autowired
    private AddCommand addCommand;

    @Autowired
    private ConfigService configService;

    @Autowired
    private BasketCommandRunner runner;

    private volatile boolean exitRequested = false;


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        if (args.length > 1) {
            configService.read(new FileReader(args[1]));
        } else {
            ((DefaultConfigService) configService).buildDefault();
        }

        Log.info("Welcome to basket. Type 'help' to see available commands");

        Scanner scanner = new Scanner(System.in);

        Thread userInputThread = new Thread(new Runnable() {
            public void run() {
                while (!exitRequested) {

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
                }
            }
        });
        userInputThread.start();
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public void setRunner(BasketCommandRunner runner) {
        this.runner = runner;
    }
}

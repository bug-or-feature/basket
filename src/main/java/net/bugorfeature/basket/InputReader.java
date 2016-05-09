package net.bugorfeature.basket;

import java.io.FileReader;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.cli.util.Log;

import net.bugorfeature.basket.command.AddCommand;
import net.bugorfeature.basket.command.BasketCommandRunner;
import net.bugorfeature.basket.command.ExitCommand;
import net.bugorfeature.basket.command.HelpCommand;
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

        BasketCommandRunner runner = new BasketCommandRunner("basket");
        runner.addCommand(new HelpCommand(runner));
        runner.addCommand(totalCommand);
        runner.addCommand(addCommand);
        runner.addCommand(new ExitCommand());

        Log.info("Welcome to basket. Type 'help' to see available commands");

        Scanner scanner = new Scanner(System.in);

        while (true) { // loop forever

            //  prompt for the user's name
            Log.infoPrint("> ");

            // accept input
            String command = scanner.nextLine();

            int exitCode = runner.runAndHandleErrors(command.split("\\s+"));
            if (exitCode != 0 || "exit".equals(command)) {
                System.exit(exitCode);
            }
        }
    }
}

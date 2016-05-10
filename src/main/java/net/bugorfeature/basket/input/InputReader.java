package net.bugorfeature.basket.input;

import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.cli.util.Log;

import net.bugorfeature.basket.service.ConfigService;
import net.bugorfeature.basket.service.DefaultConfigService;

/**
 * Does the work of accepting command line input
 *
 * @author Andy Geach
 */
public class InputReader implements CommandLineRunner {

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserInputThread inputThread;

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

        Thread userInputThread = new Thread(inputThread, "inputThread");
        userInputThread.start();
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}

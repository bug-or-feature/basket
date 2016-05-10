package net.bugorfeature.basket;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.bugorfeature.basket.command.AddCommand;
import net.bugorfeature.basket.command.BasketCommandRunner;
import net.bugorfeature.basket.command.ExitCommand;
import net.bugorfeature.basket.command.HelpCommand;
import net.bugorfeature.basket.command.TotalCommand;
import net.bugorfeature.basket.input.InputReader;
import net.bugorfeature.basket.input.UserInputThread;
import net.bugorfeature.basket.model.Basket;
import net.bugorfeature.basket.model.BasketImpl;
import net.bugorfeature.basket.service.ConfigService;
import net.bugorfeature.basket.service.DefaultConfigService;
import net.bugorfeature.basket.service.DefaultPricingService;
import net.bugorfeature.basket.service.PricingService;

/**
 * Spring managed basket application
 *
 * @author Andy Geach
 */
@SpringBootApplication
public class SpringCommandLineApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringCommandLineApplication.class);
        app.setWebEnvironment(false);
        app.run(args);
    }

    @Bean
    public ConfigService configService() {
        return new DefaultConfigService();
    }

    @Bean
    public PricingService pricingService() {
        DefaultPricingService pricingService = new DefaultPricingService();
        pricingService.setConfigService(configService());
        return pricingService;
    }

    @Bean
    public Basket basket() {
        return new BasketImpl();
    }

    @Bean
    public InputReader stdInputReader() {
        return new InputReader();
    }

    @Bean
    public UserInputThread inputThread() {
        UserInputThread inputThread = new UserInputThread();
        inputThread.setRunner(commandRunner());
        inputThread.setScanner(new Scanner(System.in));
        return inputThread;
    }

    @Bean
    public BasketCommandRunner commandRunner() {
        return new BasketCommandRunner("basket");
    }

    @Bean
    public TotalCommand totalCommand() {
        TotalCommand totalCommand = new TotalCommand();
        totalCommand.setPricingService(pricingService());
        totalCommand.setBasket(basket());
        return totalCommand;
    }

    @Bean
    public AddCommand addCommand() {
        AddCommand addCommand = new AddCommand();
        addCommand.setBasket(basket());
        addCommand.setConfigService(configService());
        return addCommand;
    }

    @Bean
    public HelpCommand helpCommand() {
        return new HelpCommand();
    }

    @Bean
    public ExitCommand exitCommand() {
        return new ExitCommand();
    }
}

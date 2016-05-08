package net.bugorfeature.basket.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cli.command.AbstractCommand;
import org.springframework.boot.cli.command.HelpExample;
import org.springframework.boot.cli.command.options.OptionHelp;
import org.springframework.boot.cli.command.status.ExitStatus;
import org.springframework.boot.cli.util.Log;

import net.bugorfeature.basket.model.Basket;
import net.bugorfeature.basket.model.ShoppingItem;
import net.bugorfeature.basket.service.ConfigService;
import net.bugorfeature.basket.service.PricingService;

/**
 * Command that adds item to the basket
 *
 * @author Andy Geach
 */
public class AddCommand extends AbstractCommand {

    @Autowired
    public ConfigService configService;

    @Autowired
    public Basket basket;


    public AddCommand() {
        super("add", "Add items to the basket");
    }

    @Override
    public String getUsageHelp() {
        return "<item> <count>";
    }

    /*@Override
    public Collection<HelpExample> getExamples() {
        List<HelpExample> examples = new ArrayList<HelpExample>();
        examples.add(new HelpExample("Add one Apple", "add APPLE 1"));
        examples.add(new HelpExample("Add three Peaches", "add PEACH 3"));
        return examples;
    }*/

    /**
     * Run the command.
     *
     * @param args command arguments (this will not include the command itself)
     * @return the outcome of the command
     * @throws Exception if the command fails
     */
    @Override
    public ExitStatus run(String... args) throws Exception {

        try {
            // check we have two args
            if (args.length != 2) {
                throw new IllegalArgumentException("add command must have two arguments");
            }

            // check first matches one of our products
            ShoppingItem item = null;
            try {
                item = ShoppingItem.valueOf(args[0]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown product: " + args[0]);
            }
            if (!configService.itemList().contains(item)) {
                throw new IllegalArgumentException(String.format("Shopping item '%s' is not configured", item));
            }

            // convert second to integer
            int count = Integer.decode(args[1]);

            // if integer is less than minimum give warning
            int minimum = configService.getMinimumForItem(item);
            if (count < minimum) {
                Log.info(String.format("Warning - input '%d' is less than the configured minimum '%d'", count, minimum));
            }

            // add count number of items to basket
            for (int i = 0; i < count; i++) {
                basket.addItem(item);
            }

        } catch (Exception ex) {
            Log.error(ex.getMessage());
            return ExitStatus.ERROR;
        }

        return ExitStatus.OK;
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}

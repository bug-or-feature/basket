package net.bugorfeature.basket.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cli.command.AbstractCommand;
import org.springframework.boot.cli.command.status.ExitStatus;
import org.springframework.boot.cli.util.Log;
import org.springframework.stereotype.Component;

import net.bugorfeature.basket.model.Basket;
import net.bugorfeature.basket.service.PricingService;

/**
 * Display the total cost of the basket
 *
 * @author Andy Geach
 */
@Component
public class TotalCommand extends AbstractCommand  {

    @Autowired
    public PricingService pricingService;

    @Autowired
    public Basket basket;


    public TotalCommand() {
        super("total", "Show the basket total cost");
    }

    /**
     * Run the command.
     *
     * @param args command arguments (this will not include the command itself)
     * @return the outcome of the command
     * @throws Exception if the command fails
     */
    @Override
    public ExitStatus run(String... args) throws Exception {
        Log.info("Total: " + pricingService.getTotal(basket.getItems()));
        return ExitStatus.OK;
    }

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}

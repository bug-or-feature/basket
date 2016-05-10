package net.bugorfeature.basket.command

import net.bugorfeature.basket.model.Basket
import net.bugorfeature.basket.model.ShoppingItem
import net.bugorfeature.basket.service.ConfigService
import net.bugorfeature.basket.service.PricingService
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.springframework.boot.cli.command.status.ExitStatus
import spock.lang.Specification
/**
 * Specification for TotalCommand
 *
 * @author Andy Geach
 */
class TotalCommandSpec extends Specification {

    @Rule
    SystemOutRule output = new SystemOutRule().enableLog();

    ConfigService mockConfigService
    PricingService mockPricingService
    Basket mockBasket
    ExitStatus status
    TotalCommand command

    def setup() {
        command = new TotalCommand()
        mockPricingService = Mock(PricingService)
        mockBasket = Mock(Basket)
        command.setPricingService(mockPricingService)
        command.setBasket(mockBasket)
    }


    def "get total for single item"() {
        setup:
            1 * mockPricingService.getTotal(_) >> new BigDecimal("0.25")
            1 * mockBasket.getItems() >> [ShoppingItem.APPLE]

        when:
            status = command.run()

        then:
            status == ExitStatus.OK
            output.getLog().contains("0.25")
    }
}

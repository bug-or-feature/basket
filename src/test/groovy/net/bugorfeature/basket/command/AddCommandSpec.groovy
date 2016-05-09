package net.bugorfeature.basket.command

import net.bugorfeature.basket.model.Basket
import net.bugorfeature.basket.model.ShoppingItem
import net.bugorfeature.basket.service.ConfigService
import net.bugorfeature.basket.service.PricingService
import org.junit.Rule
import org.springframework.boot.cli.command.status.ExitStatus
import org.springframework.boot.test.OutputCapture
import spock.lang.Specification

/**
 * Specification for AddCommand
 *
 * @author Andy Geach
 */
class AddCommandSpec extends Specification {

    @Rule
    OutputCapture output = new OutputCapture()

    ConfigService mockConfigService
    PricingService mockPricingService
    Basket mockBasket
    ExitStatus status
    AddCommand command


    def setup() {
        command = new AddCommand()
        mockConfigService = Mock(ConfigService)
        mockBasket = Mock(Basket)
        command.setConfigService(mockConfigService)
        command.setBasket(mockBasket)
    }

    def "add command"() {
        given:
            1 * mockConfigService.itemList() >> [ShoppingItem.APPLE]
            1 * mockConfigService.getMinimumForItem(_) >> 1
            1 * mockBasket.addItem(_)

        when:
            status = command.run("APPLE", "1")

        then:
            status == ExitStatus.OK
    }

    def "wrong argument count results in error"() {
        when:
            status = command.run("APPLE")

        then:
            status == ExitStatus.ERROR
            output.toString().contains("must have two arguments")
    }

    def "unknown item results in error"() {
        when:
            status = command.run("PEAR", "1")

        then:
            status == ExitStatus.ERROR
            output.toString().contains("No enum constant")
    }

    def "unconfigured item results in error"() {
        given:
            1 * mockConfigService.itemList() >> []

        when:
            status = command.run("APPLE", "1")

        then:
            status == ExitStatus.ERROR
            output.toString().contains("is not configured")
    }

    def "item count less than the minimum results in warning"() {
        given:
            1 * mockConfigService.itemList() >> [ShoppingItem.APPLE]
            1 * mockConfigService.getMinimumForItem(_) >> 5

        when:
            status = command.run("APPLE", "1")

        then:
            status == ExitStatus.OK
            output.toString().contains("less than the configured minimum")
    }

    def "usage"() {
        expect:
            command.getUsageHelp().contains("<item> <count>")
    }
}

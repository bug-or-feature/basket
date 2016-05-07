package net.bugorfeature.basket.service

import net.bugorfeature.basket.model.ShoppingItem
import spock.lang.Specification

/**
 * Specification for DefaultPricingService
 *
 * @author Andy Geach
 */
class DefaultPricingServiceSpec extends Specification {

    DefaultPricingService service
    ConfigService mockConfigService
    BigDecimal result;

    def setup() {
        service = new DefaultPricingService()
        mockConfigService = Mock(ConfigService)
        service.setConfigService(mockConfigService)
    }

    void 'one apple priced at 0.25 results in total of 0.25'() {

        given:
            1 * mockConfigService.getPriceForItem(_) >> new BigDecimal("0.25")

        when:
            result = service.getTotal([ShoppingItem.APPLE])

        then:
            assert result == new BigDecimal("0.25")
    }

    void 'two apples priced at 0.25 results in total of 0.50'() {

        given:
            2 * mockConfigService.getPriceForItem(_) >> new BigDecimal("0.25")

        when:
            result = service.getTotal([ShoppingItem.APPLE, ShoppingItem.APPLE])

        then:
            assert result == new BigDecimal("0.50")
    }
}

package net.bugorfeature.basket.service

import net.bugorfeature.basket.model.ShoppingItem
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Specification for StaticConfigService
 *
 * @author Andy Geach
 */
class StaticConfigServiceSpec extends Specification {

    ConfigService service

    @Unroll("Configured price - item: #item, price: #price")
    def "check prices"(ShoppingItem item, BigDecimal price) {

        setup:
            service = new StaticConfigService()
            service.buildDefault()

        expect:
            service.getPriceForItem(item) == price

        where:
            item                | price
            ShoppingItem.BANANA | new BigDecimal("0.10")
            ShoppingItem.ORANGE | new BigDecimal("0.20")
            ShoppingItem.APPLE  | new BigDecimal("0.30")
            ShoppingItem.LEMON  | new BigDecimal("0.40")
            ShoppingItem.PEACH  | new BigDecimal("0.50")
    }

    def "missing config throws exception for price"() {

        setup:
            service = new StaticConfigService()
            service.setConfigForItem(ShoppingItem.BANANA, new BigDecimal("0.50"), 0)

        when:
            service.getPriceForItem(ShoppingItem.APPLE)

        then:
            thrown(IllegalArgumentException)
    }


    @Unroll("Configured minimum - item: #item, minimum: #minimum")
    def "check minimums"(ShoppingItem item, int minimum) {
        setup:
            service = new StaticConfigService()
            service.buildDefault()

        expect:
            service.getMinimumForItem(item) == minimum

        where:
            item                | minimum
            ShoppingItem.BANANA | 1
            ShoppingItem.ORANGE | 1
            ShoppingItem.APPLE  | 1
            ShoppingItem.LEMON  | 1
            ShoppingItem.PEACH  | 1
    }

    def "missing config throws exception for minimum"() {

        setup:
            service = new StaticConfigService()
            service.setConfigForItem(ShoppingItem.BANANA, new BigDecimal("0.50"), 0)

        when:
            service.getMinimumForItem(ShoppingItem.APPLE)

        then:
            thrown(IllegalArgumentException)
    }

    def "config list contains all shopping items"() {
        setup:
            service = new StaticConfigService()
            service.buildDefault()
        expect:
            service.itemList().containsAll(ShoppingItem.values())
    }
}

package net.bugorfeature.basket.model

import spock.lang.Specification

/**
 * Spec for basket implmentation
 *
 * @author Andy Geach
 */
class BasketSpec extends Specification {

    BasketImpl basket

    def "add"() {
        setup:
            basket = new BasketImpl()

        when:
            basket.addItem(ShoppingItem.APPLE)

        then:
            assert basket.getItems()
            assert basket.getItems().contains(ShoppingItem.APPLE)
            assert basket.getItems().size() == 1
    }

    def "filter"() {
        setup:
            basket = new BasketImpl()

        when:
            basket.addItem(ShoppingItem.APPLE)
            basket.addItem(ShoppingItem.ORANGE)
            basket.addItem(ShoppingItem.PEACH)

        then:
            assert basket.getItemsOfType(ShoppingItem.APPLE).contains(ShoppingItem.APPLE)
            assert basket.getItemsOfType(ShoppingItem.APPLE).size() == 1
    }

}

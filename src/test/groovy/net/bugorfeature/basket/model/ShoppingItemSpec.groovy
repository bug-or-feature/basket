package net.bugorfeature.basket.model

import spock.lang.Specification
/**
 * Shopping item spec
 *
 * @author Andy Geach
 */
class ShoppingItemSpec extends Specification {

    def "valueOf"() {

        expect:
            ShoppingItem.valueOf("APPLE") == ShoppingItem.APPLE
            ShoppingItem.valueOf("ORANGE") == ShoppingItem.ORANGE
            ShoppingItem.valueOf("LEMON") == ShoppingItem.LEMON
            ShoppingItem.valueOf("BANANA") == ShoppingItem.BANANA
            ShoppingItem.valueOf("PEACH") == ShoppingItem.PEACH
    }

}

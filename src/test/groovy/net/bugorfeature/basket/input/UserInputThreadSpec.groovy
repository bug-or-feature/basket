package net.bugorfeature.basket.input

import net.bugorfeature.basket.command.BasketCommandRunner
import spock.lang.Specification

/**
 * Specification for UserInputThread
 *
 * @author Andy Geach
 */
class UserInputThreadSpec extends Specification {

    UserInputThread thread
    BasketCommandRunner mockRunner

    def setup() {
        thread = new UserInputThread()
        mockRunner = Mock()
        thread.setRunner(mockRunner)
        thread.setMaxLoops(1)
    }

    def "command given to user input causes the command to be passed to runner"() {
        when:
            thread.setScanner(new Scanner("total"))
            thread.run()

        then:
            1 * mockRunner.runAndHandleErrors(_) >> 0
    }
}

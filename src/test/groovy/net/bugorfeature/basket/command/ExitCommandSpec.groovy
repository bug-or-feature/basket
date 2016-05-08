package net.bugorfeature.basket.command

import net.bugorfeature.basket.OutputCapture
import org.junit.Rule
import org.springframework.boot.cli.command.status.ExitStatus
import spock.lang.Specification

/**
 * Specification for ExitCommand
 *
 * @author Andy Geach
 */
class ExitCommandSpec extends Specification {

    @Rule
    OutputCapture output = new OutputCapture()

    def "exit command"() {
        setup:
            ExitCommand command = new ExitCommand()
            ExitStatus status

        when:
            status = command.run()

        then:
            status == ExitStatus.OK
            output.toString().contains("goodbye")
    }
}

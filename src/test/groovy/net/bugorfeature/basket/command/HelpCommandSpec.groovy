package net.bugorfeature.basket.command

import net.bugorfeature.basket.OutputCapture

import org.junit.Rule
import org.springframework.boot.cli.command.status.ExitStatus
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Specification for HelpCommand
 *
 * @author Andy Geach
 */
class HelpCommandSpec extends Specification {

    @Rule
    OutputCapture output = new OutputCapture()

    @Ignore
    def "help command"() {
        setup:
            HelpCommand command = new HelpCommand()
            ExitStatus status

        when:
            status = command.run()

        then:
            status == ExitStatus.OK
            output.toString().contains("help [command]")
    }
}

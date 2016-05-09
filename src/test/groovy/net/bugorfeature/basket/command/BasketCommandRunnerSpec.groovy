package net.bugorfeature.basket.command

import net.bugorfeature.basket.OutputCapture
import org.junit.Rule
import org.springframework.boot.cli.command.status.ExitStatus
import spock.lang.Specification

/**
 * Specification for BasketCommandRunner
 *
 * @author Andy Geach
 */
class BasketCommandRunnerSpec extends Specification {

    @Rule
    OutputCapture output = new OutputCapture()

    def "missing or unkown command results in OK status"() {
        setup:
            BasketCommandRunner runner = new BasketCommandRunner("blah")
            ExitStatus status

        when:
            status = runner.run("foo")

        then:
            status == ExitStatus.OK
            output.toString().contains("Unknown command")
    }

    def "no argument to runner results in exception"() {
        setup:
            BasketCommandRunner runner = new BasketCommandRunner("foo")

        when:
            status = runner.run()

        then:
            thrown(MissingArgsException)
    }

    def "found command is executed"() {
        setup:
            BasketCommandRunner runner = new BasketCommandRunner("blah")
            runner.addCommand(new ExitCommand())
            ExitStatus status

        when:
            status = runner.run("exit")

        then:
            status == ExitStatus.OK
            output.toString().contains("goodbye")
    }
}

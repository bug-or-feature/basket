package net.bugorfeature.basket.command

import net.bugorfeature.basket.test.OutputCapture
import org.junit.Rule
import org.springframework.boot.cli.command.Command
import org.springframework.boot.cli.command.CommandRunner
import org.springframework.boot.cli.command.NoSuchCommandException
import org.springframework.boot.cli.command.status.ExitStatus
import spock.lang.Specification

/**
 * Specification for HelpCommand
 *
 * @author Andy Geach
 */
class HelpCommandSpec extends Specification {

    @Rule
    OutputCapture output = new OutputCapture()

    CommandRunner commandRunner
    CommandIterator iterator

    def setup() {
        commandRunner = Mock(CommandRunner)
        iterator = Mock(CommandIterator)
    }


    def "help with command supplied"() {

        given:
            HelpCommand command = new HelpCommand(commandRunner)
            ExitStatus status
            1 * commandRunner.iterator() >> iterator
            2 * iterator.hasNext() >>> [true, false]
            1 * iterator.next() >>> [new ExitCommand()]

        when:
            status = command.run()

        then:
            status == ExitStatus.OK
            output.toString().contains("Available commands are")
    }

    def "help with valid command"() {

        given:
            HelpCommand command = new HelpCommand(commandRunner)
            ExitStatus status
            1 * commandRunner.iterator() >> iterator
            1 * iterator.hasNext() >>> [true, false]
            1 * iterator.next() >>> [new ExitCommand()]

        when:
            status = command.run("exit")

        then:
            status == ExitStatus.OK
            output.toString().contains("exit")
            output.toString().contains("Exit the application")
    }

    def "help with unknown command"() {

        given:
            HelpCommand command = new HelpCommand(commandRunner)
            ExitStatus status
            1 * commandRunner.iterator() >> iterator
            2 * iterator.hasNext() >>> [true, false]
            1 * iterator.next() >>> [new ExitCommand()]

        when:
            status = command.run("blah")

        then:
            thrown(NoSuchCommandException)
    }

    def "usage"() {
        given:
            HelpCommand command = new HelpCommand(commandRunner)

        expect:
            command.getUsageHelp().contains("[command]")
    }

    def "usage and help is output when the command supports it"() {
        given:
            HelpCommand command = new HelpCommand(commandRunner)
            ExitStatus status
            1 * commandRunner.iterator() >> iterator
            1 * iterator.hasNext() >>> [true, false]
            1 * iterator.next() >>> [new AddCommand()]

        when:
            status = command.run("add")

        then:
            status == ExitStatus.OK
            output.toString().contains("Add items to the basket")
            output.toString().contains("usage")
            output.toString().contains("'add PEACH 3'")
    }

    private class CommandIterator implements Iterator<Command> {
        @Override
        boolean hasNext() {
            return true
        }

        @Override
        Command next() {
            return null
        }
    }
}

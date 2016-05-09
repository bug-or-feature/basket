package net.bugorfeature.basket.command;

import javax.annotation.PostConstruct;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cli.command.Command;
import org.springframework.boot.cli.command.CommandRunner;
import org.springframework.boot.cli.command.status.ExitStatus;
import org.springframework.boot.cli.util.Log;

/**
 * Extends {@link org.springframework.boot.cli.command.CommandRunner} to continue execution in case of un unkown command
 *
 * @author Andy Geach
 */
public class BasketCommandRunner extends CommandRunner {

    public BasketCommandRunner(String name) {
        super(name);
    }

    @Autowired
    private HelpCommand helpCommand;

    @Autowired
    private AddCommand addCommand;

    @Autowired
    private TotalCommand totalCommand;

    @Autowired
    private ExitCommand exitCommand;

    @PostConstruct
    public void init() {
        addCommand(helpCommand);
        addCommand(addCommand);
        addCommand(totalCommand);
        addCommand(exitCommand);
    }

    /**
     * Parse the arguments and run a suitable command.
     *
     * @param args the arguments
     * @return the outcome of the command
     * @throws Exception if the command fails
     */
    @Override
    protected ExitStatus run(String... args) throws Exception {
        if (args.length == 0) {
            throw new MissingArgsException();
        }
        String commandName = args[0];
        String[] commandArguments = Arrays.copyOfRange(args, 1, args.length);
        Command command = findCommand(commandName);
        if (command == null) {
            Log.error("Unknown command: " + commandName);
            return ExitStatus.OK;
        }
        beforeRun(command);
        try {
            return command.run(commandArguments);
        } finally {
            afterRun(command);
        }
    }
}

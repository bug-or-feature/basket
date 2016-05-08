package net.bugorfeature.basket.command;

import org.springframework.boot.cli.command.AbstractCommand;
import org.springframework.boot.cli.command.status.ExitStatus;
import org.springframework.boot.cli.util.Log;

/**
 * Exit the application
 *
 * @author Andy Geach
 */
public class ExitCommand extends AbstractCommand  {

    public ExitCommand() {
        super("exit", "Exit the application");
    }

    /**
     * Run the command.
     *
     * @param args command arguments (this will not include the command itself)
     * @return the outcome of the command
     * @throws Exception if the command fails
     */
    @Override
    public ExitStatus run(String... args) throws Exception {
        Log.info("goodbye");
        return ExitStatus.OK;
    }
}

package net.bugorfeature.basket.command;

import org.springframework.boot.cli.command.CommandException;

/**
 * This only exists because {@link org.springframework.boot.cli.command.NoArgumentsException} is not public
 *
 *
 * @see BasketCommandRunner
 * @author Andy Geach
 */
public class MissingArgsException extends CommandException {
    private static final long serialVersionUID = 1L;
}

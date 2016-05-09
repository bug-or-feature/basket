/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bugorfeature.basket.command;

import java.util.Iterator;

import org.springframework.boot.cli.command.AbstractCommand;
import org.springframework.boot.cli.command.Command;
import org.springframework.boot.cli.command.CommandRunner;
import org.springframework.boot.cli.command.NoSuchCommandException;
import org.springframework.boot.cli.command.status.ExitStatus;
import org.springframework.boot.cli.util.Log;

/**
 * Hep command. Help for individual commands, or list of available commands
 *
 * Copied/adapted from {@link org.springframework.boot.cli.command.core.HelpCommand}
 *
 * @see org.springframework.boot.cli.command.core.HelpCommand
 */
public class HelpCommand extends AbstractCommand {

    private CommandRunner runner;

    public HelpCommand(CommandRunner commandRunner) {
        super("help", "Get help on commands");
        this.runner = commandRunner;
    }

    @Override
    public String getUsageHelp() {
        return "[command]";
    }

    @Override
    public ExitStatus run(String... args) throws Exception {
        if (args.length == 0) {
            availableCommands();
            return ExitStatus.OK;
        }
        String commandName = args[0];
        for (Command command : this.runner) {
            if (command.getName().equals(commandName)) {
                Log.info(command.getName() + " - " + command.getDescription());
                Log.info("");
                if (command.getUsageHelp() != null) {
                    Log.info("usage: " + command.getName() + " " + command.getUsageHelp());
                    Log.info("");
                }
                if (command.getHelp() != null) {
                    Log.info(command.getHelp());
                }
                return ExitStatus.OK;
            }
        }
        throw new NoSuchCommandException(commandName);
    }

    private void availableCommands() {
        Log.info("");
        Log.info("Available commands are:");

        Iterator<Command> iterator = runner.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            String usageHelp = command.getUsageHelp();
            String description = command.getDescription();
            Log.info(String.format("\n  %1$s %2$-15s\n    %3$s", command.getName(),
                    usageHelp == null ? "" : usageHelp,
                    description == null ? "" : description));
        }
        Log.info("");
    }
}

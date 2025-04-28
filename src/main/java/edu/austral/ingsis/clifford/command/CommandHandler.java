package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.command.factory.CommandFactory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.Arrays;
import java.util.Map;

public class CommandHandler {
  private final Map<String, CommandFactory> commandFactories;

  public CommandHandler(Map<String, CommandFactory> commandFactories) {
    this.commandFactories = commandFactories;
  }

  public Result handleCommand(String command, FileSystem fileSystem) {
    String[] args = command.split(" ");

    try {
      Command cmd = getCommand(args);
      return cmd.execute(fileSystem);
    } catch (IllegalArgumentException e) {
      return new Result(e.getMessage(), fileSystem);
    } catch (IllegalStateException e) {
      return new Result(e.getMessage(), fileSystem);
    }
  }

  private Command getCommand(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("No command specified");
    }
    String command = args[0];
    CommandFactory factory = commandFactories.get(command);
    if (factory == null) {
      throw new IllegalArgumentException("Unknown command: " + command);
    }
    return factory.create(Arrays.copyOfRange(args, 1, args.length));
  }
}

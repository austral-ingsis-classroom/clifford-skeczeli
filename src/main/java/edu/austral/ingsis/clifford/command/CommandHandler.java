package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.command.factory.CdFactory;
import edu.austral.ingsis.clifford.command.factory.CommandFactory;
import edu.austral.ingsis.clifford.command.factory.LsFactory;
import edu.austral.ingsis.clifford.command.factory.MkdirFactory;
import edu.austral.ingsis.clifford.command.factory.PwdFactory;
import edu.austral.ingsis.clifford.command.factory.RmFactory;
import edu.austral.ingsis.clifford.command.factory.TouchFactory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.Arrays;
import java.util.Map;

public class CommandHandler {
  // todo: sacar check de args de los commands individuales y pasarla al handler
  // todo: pasar map (commands) como argumento al handler!!!
  private final Map<String, CommandFactory> commands =
      Map.of(
          "cd", new CdFactory(),
          "ls", new LsFactory(),
          "mkdir", new MkdirFactory(),
          "pwd", new PwdFactory(),
          "rm", new RmFactory(),
          "touch", new TouchFactory());

  public String handleCommand(String command, FileSystem fileSystem) {
    String[] args = command.split(" ");

    try {
      Command cmd = getCommand(args);
      return cmd.execute(fileSystem);
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    } catch (IllegalStateException e) {
      return e.getMessage();
    }
  }

  private Command getCommand(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("No command specified");
    }
    String command = args[0];
    CommandFactory factory = commands.get(command);
    if (factory == null) {
      throw new IllegalArgumentException("Unknown command: " + command);
    }
    return factory.create(Arrays.copyOfRange(args, 1, args.length));
  }
}

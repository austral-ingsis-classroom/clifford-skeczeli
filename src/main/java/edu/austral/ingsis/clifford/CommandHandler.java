package edu.austral.ingsis.clifford;

import java.util.Arrays;
import java.util.Map;

public class CommandHandler {
  private final Map<String, Command> commands =
      Map.of(
          "cd", new CdCommand(),
          "ls", new LsCommand(),
          "mkdir", new MkdirCommand(),
          "pwd", new PwdCommand(),
          "rm", new RmCommand(),
          "touch", new TouchCommand());

  public String handleCommand(String command, FileSystem fileSystem) {
    String[] args = command.split(" ");
    if (args.length == 0) {
      return "Error: No command specified";
    }
    Command cmd = getCommand(args[0]);
    if (cmd == null) {
      return "Error: Unknown command";
    }
    String[] slicedArgs = Arrays.copyOfRange(args, 1, args.length);
    return cmd.execute(fileSystem, slicedArgs);
  }

  private Command getCommand(String command) {
    return commands.get(command);
  }
}

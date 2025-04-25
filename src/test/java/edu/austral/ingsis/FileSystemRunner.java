package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.CommandHandler;
import edu.austral.ingsis.clifford.command.factory.CdFactory;
import edu.austral.ingsis.clifford.command.factory.CommandFactory;
import edu.austral.ingsis.clifford.command.factory.LsFactory;
import edu.austral.ingsis.clifford.command.factory.MkdirFactory;
import edu.austral.ingsis.clifford.command.factory.PwdFactory;
import edu.austral.ingsis.clifford.command.factory.RmFactory;
import edu.austral.ingsis.clifford.command.factory.TouchFactory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSystemRunner {
  private final Map<String, CommandFactory> commands =
      Map.of(
          "cd", new CdFactory(),
          "ls", new LsFactory(),
          "mkdir", new MkdirFactory(),
          "pwd", new PwdFactory(),
          "rm", new RmFactory(),
          "touch", new TouchFactory());

  private final CommandHandler commandHandler = new CommandHandler(commands);
  private final FileSystem fileSystem = new FileSystem();

  public FileSystem getFileSystem() {
    return fileSystem;
  }

  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String command : commands) {
      results.add(commandHandler.handleCommand(command, fileSystem));
    }
    return results;
  }
}

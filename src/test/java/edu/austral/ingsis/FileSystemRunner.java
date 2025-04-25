package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.CommandHandler;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunner {
  private final CommandHandler commandHandler = new CommandHandler();
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

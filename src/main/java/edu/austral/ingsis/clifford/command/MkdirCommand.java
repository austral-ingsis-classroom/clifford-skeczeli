package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class MkdirCommand implements Command {
  private final String directory;

  public MkdirCommand(String directory) {
    this.directory = directory;
  }

  @Override
  public Result execute(FileSystem fileSystem) {
    FileSystem newFs = fileSystem.addNode(new Directory(directory));
    return new Result("'" + directory + "' directory created", newFs);
  }
}

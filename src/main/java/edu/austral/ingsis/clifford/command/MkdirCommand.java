package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class MkdirCommand implements Command {
  private final String directory;

  public MkdirCommand(String path) {
    this.directory = path;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.addNode(new Directory(directory, fileSystem.getCurrent()));
    return "'" + directory + "' directory created";
  }
}

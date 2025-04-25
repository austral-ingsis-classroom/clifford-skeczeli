package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.File;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class TouchCommand implements Command {
  private final String fileName;

  public TouchCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.addNode(new File(fileName));
    return "'" + fileName + "' file created";
  }
}

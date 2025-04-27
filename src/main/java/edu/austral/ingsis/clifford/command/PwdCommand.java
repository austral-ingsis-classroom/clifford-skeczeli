package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class PwdCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem) {
    return fileSystem.getCurrentPathString();
  }
}

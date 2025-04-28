package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class PwdCommand implements Command {
  @Override
  public Result execute(FileSystem fileSystem) {
    return new Result(fileSystem.getCurrentPathString(), fileSystem);
  }
}

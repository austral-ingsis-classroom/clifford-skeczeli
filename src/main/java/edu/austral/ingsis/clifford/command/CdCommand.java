package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class CdCommand implements Command {
  private final String target;

  public CdCommand(String target) {
    this.target = target;
  }

  @Override
  public Result execute(FileSystem fs) {
    try {
      FileSystem newFs = fs.changeDirectory(target);
      return new Result("moved to directory '" + newFs.getCurrentDirectory().name() + "'", newFs);
    } catch (IllegalStateException e) {
      return new Result("'" + target + "' directory does not exist", fs);
    }
  }
}

package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.FileSystem;

public final class CdCommand implements Command {
  private final String target;

  public CdCommand(String target) {
    this.target = target;
  }

  @Override
  public String execute(FileSystem fs) {
    try {
      fs.changeDirectory(target);
      return "moved to directory '" + fs.getCurrentDirectory().name() + "'";
    } catch (IllegalStateException e) {
      return "'" + target + "' directory does not exist";
    }
  }
}

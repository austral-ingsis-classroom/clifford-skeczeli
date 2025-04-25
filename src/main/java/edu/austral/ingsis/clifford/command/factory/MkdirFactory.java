package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.MkdirCommand;

public class MkdirFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Mkdir command requires 1 argument");
    }
    String directory = args[0];
    if (directory.contains("/") || directory.contains(" ")) {
      throw new IllegalArgumentException("Invalid directory name");
    }
    return new MkdirCommand(directory);
  }
}

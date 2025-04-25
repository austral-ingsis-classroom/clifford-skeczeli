package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.TouchCommand;

public class TouchFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Touch command requires 1 argument");
    }
    String fileName = args[0];
    if (fileName.contains("/") || fileName.contains(" ")) {
      throw new IllegalArgumentException("Invalid file name");
    } else {
      return new TouchCommand(fileName);
    }
  }
}

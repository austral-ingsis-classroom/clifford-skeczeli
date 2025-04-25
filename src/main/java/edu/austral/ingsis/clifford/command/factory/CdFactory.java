package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.CdCommand;
import edu.austral.ingsis.clifford.command.Command;

public class CdFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Invalid number of arguments for CdCommand");
    }
    return new CdCommand(args[0]);
  }
}

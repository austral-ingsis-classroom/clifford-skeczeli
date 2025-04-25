package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.PwdCommand;

public class PwdFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    if (args.length >= 1) {
      throw new IllegalArgumentException("PWD command requires no arguments");
    }
    return new PwdCommand();
  }
}

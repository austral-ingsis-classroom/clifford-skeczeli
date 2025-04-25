package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.RmCommand;

public class RmFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    if (args.length == 1) {
      return new RmCommand(args[0], false);
    } else if (args.length == 2 && args[0].equals("--recursive")) {
      return new RmCommand(args[1], true);
    } else {
      throw new IllegalArgumentException("Invalid arguments for rm");
    }
  }
}

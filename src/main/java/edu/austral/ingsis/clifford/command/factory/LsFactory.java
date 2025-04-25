package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.LsCommand;

public class LsFactory implements CommandFactory {
  @Override
  public Command create(String[] args) {
    String ord;
    if (args.length == 0) {
      ord = "creation";
    } else if (args[0].equals("--ord=asc")) {
      ord = "asc";
    } else if (args[0].equals("--ord=desc")) {
      ord = "desc";
    } else {
      throw new IllegalArgumentException("Invalid argument for --ord");
    }
    return new LsCommand(ord);
  }
}

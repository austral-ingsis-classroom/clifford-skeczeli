package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;

public interface CommandFactory {
  Command create(String[] args);
}

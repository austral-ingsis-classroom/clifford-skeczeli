package edu.austral.ingsis.clifford;

public sealed interface Command
    permits CdCommand, LsCommand, MkdirCommand, PwdCommand, RmCommand, TouchCommand {
  public String execute(FileSystem fileSystem, String[] args);
}

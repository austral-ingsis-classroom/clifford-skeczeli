package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public sealed interface Command
    permits CdCommand, LsCommand, MkdirCommand, PwdCommand, RmCommand, TouchCommand {
  public Result execute(FileSystem fileSystem);
}

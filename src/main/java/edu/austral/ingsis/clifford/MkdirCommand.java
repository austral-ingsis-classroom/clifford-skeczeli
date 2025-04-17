package edu.austral.ingsis.clifford;

public final class MkdirCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, String[] args) {
    // No se puede crear directorios que tengan "/" ni espacio en el nombre.
    if (args[0].contains("/") || args[0].contains(" ")) return "Invalid directory name";
    fileSystem.addNode(new Directory(args[0], fileSystem.getCurrent()));
    return "'" + args[0] + "' directory created";
  }
}

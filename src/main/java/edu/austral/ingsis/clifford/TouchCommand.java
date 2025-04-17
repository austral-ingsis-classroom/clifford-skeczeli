package edu.austral.ingsis.clifford;

public final class TouchCommand implements Command {
  @Override
  // todo unir Touch y Mkdir en uno solo...?
  public String execute(FileSystem fileSystem, String[] args) {
    if (args[0].contains("/") || args[0].contains(" ")) return "Invalid file name";
    fileSystem.addNode(new File(args[0]));
    return "'" + args[0] + "' file created";
  }
}

package edu.austral.ingsis.clifford;

import java.util.List;

public final class RmCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, String[] args) {
    RmArgs parsed = parseArgs(args);
    String name = parsed.name();
    boolean isRecursive = parsed.isRecursive();

    Directory current = fileSystem.getCurrent();
    List<FileSystemNode> children = current.getChildren();

    return rmResult(children, name, isRecursive, current);
  }

  private static String rmResult(
      List<FileSystemNode> children, String name, boolean isRecursive, Directory current) {
    for (FileSystemNode child : children) {
      if (foundNode(child, name)) {
        if (child instanceof Directory) {
          if (!isRecursive) {
            return "cannot remove '" + name + "', is a directory";
          } else {
            current.removeChild(child);
          }
        } else {
          // podría tirar error si recibo --rec file...
          current.removeChild(child);
        }
        return "'" + name + "' removed";
      }
    }
    return "Error: '" + name + "' not found";
  }

  private record RmArgs(String name, boolean isRecursive) {}

  private RmArgs parseArgs(String[] args) {
    if (args.length == 1) return new RmArgs(args[0], false);
    if (args.length == 2 && args[0].equals("--recursive")) return new RmArgs(args[1], true);
    throw new IllegalArgumentException("Invalid arguments for rm");
  }

  private static boolean foundNode(FileSystemNode child, String name) {
    return child.getName().equals(name);
  }
}

package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;
import java.util.List;

public final class RmCommand implements Command {
  private final String nodeName;
  private final boolean isRecursive;

  public RmCommand(String nodeName, boolean isRecursive) {
    this.nodeName = nodeName;
    this.isRecursive = isRecursive;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    Directory current = fileSystem.getCurrent();
    List<FileSystemNode> children = current.getChildren();

    return rmResult(children, nodeName, isRecursive, current);
  }

  private static String rmResult(
      List<FileSystemNode> children, String name, boolean isRecursive, Directory current) {
    for (FileSystemNode child : children) {
      if (foundNode(child, name)) {
        if (child instanceof Directory) {
          if (!isRecursive) {
            throw new IllegalStateException("cannot remove '" + name + "', is a directory");
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
    throw new IllegalStateException("'" + name + "' not found");
  }

  private static boolean foundNode(FileSystemNode child, String name) {
    return child.name().equals(name);
  }
}

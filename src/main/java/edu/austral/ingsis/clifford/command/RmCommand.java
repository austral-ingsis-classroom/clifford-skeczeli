package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

public final class RmCommand implements Command {
  private final String name;
  private final boolean isRecursive;

  public RmCommand(String name, boolean isRecursive) {
    this.name = name;
    this.isRecursive = isRecursive;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    Directory current = fileSystem.getCurrentDirectory();
    FileSystemNode node = findNode(current, name);

    if (node.isDirectory() && !isRecursive) {
      throw new IllegalStateException("cannot remove '" + name + "', is a directory");
    }

    Directory updatedCurrent = current.removeChild(name);
    fileSystem.updateCurrent(updatedCurrent);
    return "'" + name + "' removed";
  }

  private FileSystemNode findNode(Directory directory, String nodeName) {
    if (!directory.hasChild(nodeName)) {
      throw new IllegalStateException("No such file or directory: " + nodeName);
    }
    return directory.findChild(nodeName);
  }
}

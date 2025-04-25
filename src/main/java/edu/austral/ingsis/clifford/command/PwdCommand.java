package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.LinkedList;

public final class PwdCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem) {
    LinkedList<String> pathParts = getPathParts(fileSystem);
    StringBuilder builder = formatPath(pathParts);
    return builder.toString();
  }

  private static StringBuilder formatPath(LinkedList<String> path) {
    if (path.size() == 1) {
      return new StringBuilder("/");
    }

    StringBuilder builder = new StringBuilder();
    for (String part : path) {
      if (!part.equals("/")) {
        builder.append('/');
        builder.append(part);
      }
    }
    return builder;
  }

  private static LinkedList<String> getPathParts(FileSystem fileSystem) {
    Directory node = fileSystem.getCurrent();
    LinkedList<String> pathParts = new LinkedList<>();
    while (node != null) {
      pathParts.addFirst(node.name());
      node = node.getParent();
    }
    return pathParts;
  }
}

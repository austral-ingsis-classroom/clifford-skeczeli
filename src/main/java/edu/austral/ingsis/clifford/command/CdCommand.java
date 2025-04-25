package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

public final class CdCommand implements Command {
  private final String target;

  public CdCommand(String target) {
    this.target = target;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    switch (target) {
      case "." -> {
        return successfulMoveMessage(fileSystem.getCurrent().name());
      }
      case ".." -> {
        return moveToParent(fileSystem);
      }
      case "/" -> {
        return moveToResolvedDirectory(fileSystem, fileSystem.getRoot());
      }
    }

    boolean isAbsolute = target.startsWith("/");
    String[] pathParts = getPathParts(target);

    if (containsInvalidSegments(isAbsolute, pathParts, target)) {
      throw new IllegalStateException("Invalid path: '" + target + "'");
    }

    FileSystemNode start = getStartingNode(fileSystem, isAbsolute);
    FileSystemNode finalNode = resolvePathFrom(start, pathParts, isAbsolute);
    ;

    if (finalNode == null)
      throw new IllegalStateException("'" + target + "' directory does not exist");
    if (nodeIsNotDir(finalNode))
      throw new IllegalStateException("'" + target + "' is not a directory");

    return moveToResolvedDirectory(fileSystem, (Directory) finalNode);
  }

  private static boolean nodeIsNotDir(FileSystemNode current) {
    return !(current instanceof Directory);
  }

  private static boolean containsInvalidSegments(
      boolean isAbsolute, String[] pathParts, String input) {
    for (int i = (isAbsolute ? 1 : 0); i < pathParts.length; i++) {
      if (pathParts[i].isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private FileSystemNode resolvePathFrom(FileSystemNode start, String[] parts, boolean isAbsolute) {
    int startIndex = isAbsolute ? 1 : 0;
    return getFinalDir(parts, startIndex, start);
  }

  private FileSystemNode getFinalDir(String[] parts, int index, FileSystemNode current) {
    if (reachedEnd(parts, index)) {
      return current;
    }

    if (!(current instanceof Directory dir)) {
      return null;
    }

    FileSystemNode next = findChild(dir, parts[index]);

    if (next == null) {
      return null;
    }

    return getFinalDir(parts, index + 1, next);
  }

  private static boolean reachedEnd(String[] parts, int index) {
    return index == parts.length;
  }

  private FileSystemNode findChild(Directory dir, String name) {
    for (FileSystemNode child : dir.getChildren()) {
      if (child.name().equals(name)) {
        return child;
      }
    }
    return null;
  }

  private static String[] getPathParts(String input) {
    return input.split("/");
  }

  private static Directory getStartingNode(FileSystem fileSystem, boolean isAbsolute) {
    return isAbsolute ? fileSystem.getRoot() : fileSystem.getCurrent();
  }

  private static String moveToParent(FileSystem fileSystem) {
    Directory parent = fileSystem.getCurrent().getParent();
    if (nodeNotRoot(parent)) {
      fileSystem.setCurrent(parent);
      return successfulMoveMessage(parent.name());
    } else {
      return successfulMoveMessage("/");
    }
  }

  private static boolean nodeNotRoot(Directory parent) {
    return parent != null;
  }

  private String moveToResolvedDirectory(FileSystem fs, Directory newDir) {
    fs.setCurrent(newDir);
    return successfulMoveMessage(newDir.name());
  }

  private static String successfulMoveMessage(String name) {
    return "moved to directory '" + name + "'";
  }
}

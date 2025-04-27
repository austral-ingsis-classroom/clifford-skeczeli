package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;
import java.util.ArrayList;
import java.util.List;

public final class LsCommand implements Command {
  private final String order;

  public LsCommand(String order) {
    this.order = order;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    List<FileSystemNode> currDirElements = fileSystem.getCurrentDirectory().getChildren();
    List<String> foundNodes = new ArrayList<>();
    for (FileSystemNode element : currDirElements) {
      foundNodes.add(element.name());
    }
    return switch (order) {
      case "creation" -> toString(foundNodes);
      case "asc" -> {
        sortAscending(foundNodes);
        yield toString(foundNodes);
      }
      case "desc" -> {
        sortDescending(foundNodes);
        yield toString(foundNodes);
      }
      default -> throw new IllegalStateException("Invalid argument for --ord");
    };
  }

  private static void sortAscending(List<String> results) {
    results.sort(String::compareTo);
  }

  private static void sortDescending(List<String> results) {
    results.sort((a, b) -> b.compareTo(a));
  }

  private String toString(List<String> results) {
    StringBuilder sb = new StringBuilder();
    for (String result : results) {
      sb.append(result).append(" ");
    }
    return sb.toString().strip();
  }
}

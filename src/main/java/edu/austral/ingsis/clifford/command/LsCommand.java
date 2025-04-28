package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
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
  public Result execute(FileSystem fileSystem) {
    List<FileSystemNode> currDirElements = fileSystem.getCurrentDirectory().getChildren();
    List<String> foundNodes = new ArrayList<>();
    for (FileSystemNode element : currDirElements) {
      foundNodes.add(element.name());
    }
    return switch (order) {
      case "creation" -> getResult(fileSystem, foundNodes);
      case "asc" -> {
        sortAscending(foundNodes);
        yield getResult(fileSystem, foundNodes);
      }
      case "desc" -> {
        sortDescending(foundNodes);
        yield getResult(fileSystem, foundNodes);
      }
      default -> throw new IllegalStateException("Invalid argument for --ord");
    };
  }

  private Result getResult(FileSystem fileSystem, List<String> foundNodes) {
    return new Result(toString(foundNodes), fileSystem);
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

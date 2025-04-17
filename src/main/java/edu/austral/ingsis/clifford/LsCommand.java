package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LsCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem, String[] args) {
    List<FileSystemNode> currDirElements = fileSystem.getCurrent().getChildren();
    List<String> foundNodes = new ArrayList<>();
    for (FileSystemNode element : currDirElements) {
      foundNodes.add(element.getName());
    }
    if (args.length == 0) return toString(foundNodes);
    String ord = args[0];
    if (isDesc(ord)) {
      sortDescending(foundNodes);
      return toString(foundNodes);
    } else if (isAsc(ord)) {
      sortAscending(foundNodes);
      return toString(foundNodes);
    }
    return "Error: Invalid argument for --ord";
  }

  private static boolean isAsc(String ord) {
    return Objects.equals(ord, "--ord=asc");
  }

  private static boolean isDesc(String ord) {
    return Objects.equals(ord, "--ord=desc");
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

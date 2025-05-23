package edu.austral.ingsis.clifford.filesystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
  private Directory root;
  private List<String> currentPath;

  public FileSystem() {
    this.root = new Directory("/");
    this.currentPath = List.of();
  }

  public FileSystem(Directory root, List<String> currentPath) {
    this.root = root;
    this.currentPath = List.copyOf(currentPath);
  }

  public Directory getRoot() {
    return root;
  }

  public List<String> getCurrentPath() {
    return new ArrayList<>(currentPath);
  }

  public Directory getCurrentDirectory() {
    return navigateTo(currentPath);
  }

  public FileSystem updateCurrent(Directory updatedCurrent) {
    return new FileSystem(
        updatePathNodes(root, currentPath, updatedCurrent), List.copyOf(currentPath));
  }

  public FileSystem addNode(FileSystemNode node) {
    Directory updatedCurrent = getCurrentDirectory().addChild(node);
    return new FileSystem(
        updatePathNodes(root, currentPath, updatedCurrent), List.copyOf(currentPath));
  }

  public FileSystem changeDirectory(String path) {
    List<String> resolvedPath = resolvePath(path);
    navigateTo(resolvedPath);
    return new FileSystem(root, List.copyOf(resolvedPath));
  }

  public String getCurrentPathString() {
    if (currentPath.isEmpty()) return "/";
    return "/" + String.join("/", currentPath);
  }

  private Directory navigateTo(List<String> path) {
    Directory pointer = root;
    for (String part : path) {
      FileSystemNode next = pointer.findChild(part);
      if (!next.isDirectory()) throw new IllegalStateException("Not a directory: " + part);
      pointer = (Directory) next;
    }
    return pointer;
  }

  private Directory updatePathNodes(Directory dir, List<String> path, Directory replacement) {
    if (path.isEmpty()) return replacement;
    String head = path.get(0);
    List<String> tail = path.subList(1, path.size());
    FileSystemNode child = dir.findChild(head);
    if (!child.isDirectory()) throw new IllegalStateException("Expected a directory: " + head);
    Directory updatedChild = updatePathNodes((Directory) child, tail, replacement);
    return dir.updateChild(updatedChild);
  }

  private List<String> resolvePath(String path) {
    List<String> result = new ArrayList<>();
    if (!path.startsWith("/")) {
      result.addAll(currentPath);
    }
    for (String part : path.split("/")) {
      if (part.isEmpty() || part.equals(".")) continue;
      if (part.equals("..")) {
        if (!result.isEmpty()) result.remove(result.size() - 1);
      } else {
        result.add(part);
      }
    }
    return result;
  }
}

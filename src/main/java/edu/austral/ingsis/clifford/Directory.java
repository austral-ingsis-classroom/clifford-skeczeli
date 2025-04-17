package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public final class Directory implements FileSystemNode {
  private final String name;
  private final List<FileSystemNode> children;
  private final Directory parent;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.children = new ArrayList<>();
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  public List<FileSystemNode> getChildren() {
    return List.copyOf(children);
  }

  public Directory getParent() {
    return parent;
  }

  void addChild(FileSystemNode node) {
    children.add(node);
  }

  void removeChild(FileSystemNode node) {
    children.remove(node);
  }
}

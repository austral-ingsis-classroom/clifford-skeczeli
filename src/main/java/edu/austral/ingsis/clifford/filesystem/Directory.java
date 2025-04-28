package edu.austral.ingsis.clifford.filesystem;

import java.util.ArrayList;
import java.util.List;

public final class Directory implements FileSystemNode {
  private final String name;
  private final List<FileSystemNode> children;

  public Directory(String name) {
    this.name = name;
    this.children = List.of();
  }

  Directory(String name, List<FileSystemNode> children) {
    this.name = name;
    this.children = List.copyOf(children);
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public List<FileSystemNode> getChildren() {
    return List.copyOf(children);
  }

  public Directory addChild(FileSystemNode node) {
    List<FileSystemNode> newChildren = new ArrayList<>(children);
    newChildren.add(node);
    return new Directory(name, newChildren);
  }

  public Directory removeChild(String childName) {
    List<FileSystemNode> newChildren = new ArrayList<>();
    for (FileSystemNode child : children) {
      if (!child.name().equals(childName)) {
        newChildren.add(child);
      }
    }
    return new Directory(name, newChildren);
  }

  public FileSystemNode findChild(String childName) {
    for (FileSystemNode child : children) {
      if (child.name().equals(childName)) return child;
    }
    throw new IllegalStateException("Child not found: " + childName);
  }

  public Directory updateChild(FileSystemNode updatedChild) {
    List<FileSystemNode> newChildren = new ArrayList<>();
    for (FileSystemNode child : children) {
      if (child.name().equals(updatedChild.name())) {
        newChildren.add(updatedChild);
      } else {
        newChildren.add(child);
      }
    }
    return new Directory(name, newChildren);
  }

  public boolean hasChild(String childName) {
    for (FileSystemNode child : children) {
      if (child.name().equals(childName)) return true;
    }
    return false;
  }
}

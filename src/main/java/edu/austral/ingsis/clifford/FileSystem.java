package edu.austral.ingsis.clifford;

public class FileSystem {
  private final Directory root;
  private Directory current;

  public FileSystem() {
    root = new Directory("/", null);
    current = root;
  }

  Directory getRoot() {
    return root;
  }

  // Or maybe move state / session elsewhere...
  Directory getCurrent() {
    return current;
  }

  void setCurrent(Directory newCurrent) {
    this.current = newCurrent;
  }

  void addNode(FileSystemNode node) {
    current.addChild(node);
  }
}

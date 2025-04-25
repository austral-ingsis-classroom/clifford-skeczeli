package edu.austral.ingsis.clifford.filesystem;

public class FileSystem {
  // todo: hacer inmutable
  private final Directory root;
  private Directory current;

  public FileSystem() {
    root = new Directory("/", null);
    current = root;
  }

  public Directory getRoot() {
    return root;
  }

  // Or maybe move state / session elsewhere...
  public Directory getCurrent() {
    return current;
  }

  public void setCurrent(Directory newCurrent) {
    this.current = newCurrent;
  }

  public void addNode(FileSystemNode node) {
    current.addChild(node);
  }
}

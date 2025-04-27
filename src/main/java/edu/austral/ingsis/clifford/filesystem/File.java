package edu.austral.ingsis.clifford.filesystem;

public record File(String name) implements FileSystemNode {
  public boolean isDirectory() {
    return false;
  }
}

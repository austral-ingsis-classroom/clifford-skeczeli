package edu.austral.ingsis.clifford;

public final class File implements FileSystemNode {
  private final String name;

  public File(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}

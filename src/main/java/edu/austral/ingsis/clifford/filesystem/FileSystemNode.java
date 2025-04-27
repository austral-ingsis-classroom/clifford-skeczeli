package edu.austral.ingsis.clifford.filesystem;

public sealed interface FileSystemNode permits Directory, File {
  String name();

  boolean isDirectory();
}

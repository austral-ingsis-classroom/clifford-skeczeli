package edu.austral.ingsis.clifford;

public sealed interface FileSystemNode permits Directory, File {
  String getName();
}

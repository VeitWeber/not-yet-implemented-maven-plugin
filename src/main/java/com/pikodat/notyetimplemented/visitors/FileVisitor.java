package com.pikodat.notyetimplemented.visitors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;


/**
 * Simple file visitor that process Java files
 *
 * @author veit@pikodat.com, 25.09.2015
 */
public class FileVisitor extends SimpleFileVisitor<Path> {
  /**
   * Method visitor to process Java methods
   */
  MethodVisitor methodVisitor = new MethodVisitor();

  /**
   * Get List with @NotYetImplemented annotations
   *
   * @return List with @NotYetImplemented annotations
   */
  public List<String> getWarnings() {
    return this.methodVisitor.getWarnings();
  }

  /**
   * Visit path and process *.java files
   *
   * @param path                Path to visit
   * @param basicFileAttributes BasicFileAttributes of the File to visit
   * @return FileVisitResult.CONTINUE
   * @throws IOException
   */
  @Override
  public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
    if (!Files.isDirectory(path))
      if (path.toString().toLowerCase().endsWith(".java"))
        processFile(path);
    return FileVisitResult.CONTINUE;
  }

  /**
   * {@inheritDoc}
   *
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Process Java files with MethodVisitor (check for @NotYetImplemented annotations)
   *
   * @param javaFile Java source file to process
   */
  private void processFile(Path javaFile) {
    CompilationUnit cu;

    // java file to open and parse
    try (FileInputStream in = new FileInputStream(javaFile.toFile())) {
      cu = JavaParser.parse(in);
      methodVisitor.visit(cu, javaFile.toAbsolutePath());
    } catch (ParseException | IOException ex) {
      ex.printStackTrace();
    }
  }
}
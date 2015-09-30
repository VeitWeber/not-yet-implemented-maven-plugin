package com.pikodat.notyetimplemented.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.pikodat.notyetimplemented.annotations.NotYetImplemented;

import java.util.ArrayList;
import java.util.List;

/**
 * Method visitor that processes all methods and select the @NotYetImplemented annotations
 *
 * @author veit.weber@gmail.com, 25.09.2015
 */
class MethodVisitor extends VoidVisitorAdapter {

  /**
   * List with @NotYetImplemented annotations
   */
  private List<String> warnings = new ArrayList<>();

  /**
   * Get List with @NotYetImplemented annotations
   *
   * @return List with @NotYetImplemented annotations
   */
  List<String> getWarnings() {
    return warnings;
  }

  /**
   * Get all annotated methods, filter for @NotYetImplemented annotations and add a warning
   *
   * @param methodDeclaration MethodDeclaration of the File
   * @param payload           Filename (String)
   */
  @Override
  public void visit(MethodDeclaration methodDeclaration, Object payload) {
    methodDeclaration.getAnnotations().stream()
        .filter(annotation -> annotation.getName().toString().equals(NotYetImplemented.class.getSimpleName()))
        .forEach(notYetImplementedAnnotation ->
            this.warnings.add("Method \"" + methodDeclaration.getName() + "\" in File \"" + payload + "\" (Line: " + notYetImplementedAnnotation.getBeginLine() + ")is annotated with \"@NotYetImplemented\"."));
  }
}


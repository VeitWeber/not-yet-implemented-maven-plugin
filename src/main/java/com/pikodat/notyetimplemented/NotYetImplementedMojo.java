package com.pikodat.notyetimplemented;

import com.pikodat.notyetimplemented.visitors.FileVisitor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple Maven Mojo to parse and process all files
 *
 * @author veit@pikodat.com, 26.09.2015
 */
@Mojo(name = NotYetImplementedMojo.MOJO_NAME,
    defaultPhase = LifecyclePhase.PACKAGE,
    requiresOnline = false, requiresProject = true,
    threadSafe = false)
public class NotYetImplementedMojo extends AbstractMojo {

  /**
   * Mojo name
   */
  public static final String MOJO_NAME = "check-not-yet-implemented";

  /**
   * Also check methods for "throw new UnsupportedOperationException"
   * todo noch implementieren
   */
  @Parameter(property = "checkException", required = true)
  protected Boolean checkException;

  /**
   * Source directory
   */
  @Parameter(property = "project.build.sourceDirectory")
  private File outputDirectory;

  /**
   * Process all source files in the directory
   *
   * @throws MojoExecutionException
   */
  public void execute() throws MojoExecutionException {
    FileVisitor fileProcessor = new FileVisitor();

    try {
      Files.walkFileTree(Paths.get(outputDirectory.getAbsolutePath()), fileProcessor);

      //iterate over list with warnings and log them as warning
      fileProcessor.getWarnings().forEach(getLog()::warn);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

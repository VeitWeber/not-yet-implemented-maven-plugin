 A tiny maven plugin that introduces the `@NotYetImplemented` annotation. Methods with that annotation generate warnings while packaging the application.

Usage is some kind of very, very simple:

* Install the plugin in your local maven repository (at this point it doesn't make sense to commit it to Maven Central).
* Add the dependecy to your pom.xml.
```language-xml
        <dependency>
            <groupId>com.pikodat</groupId>
            <artifactId>not-yet-implemented-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

* 3. Add the plugin in the `<build>`section.

```language-xml
            <plugin>
                <groupId>com.pikodat</groupId>
                <artifactId>not-yet-implemented-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <id>validation</id>
                        <goals>
                            <goal>check-not-yet-implemented</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <checkException>true</checkException>
                </configuration>
            </plugin>
```
* Annotate your not implemented methods with `@NotYetImplemented`.
```language-java
@NotYetImplemented
private List<DataPackage> readData() {
  //..
}
```

Now the maven plugin prints the following warning while packaging the application
```languange-bash
[INFO] --- not-yet-implemented-maven-plugin:1.0-SNAPSHOT:check-not-yet-implemented (validation) @ partnerbase-depositor ---
[WARNING] Method "readData" in File "<path>/Demo.java" (Line: 117)is annotated with "@NotYetImplemented".
[INFO] ---------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ---------------------------------------------------------------
[INFO] Total time: 4.183s
[INFO] Finished at: Mon Sep 28 19:29:54 CEST 2015
[INFO] Final Memory: 24M/808M
[INFO] ---------------------------------------------------------------

```

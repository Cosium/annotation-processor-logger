[![Travis branch](https://img.shields.io/travis/Cosium/annotation-processor-logger/master.svg)](https://travis-ci.org/Cosium/annotation-processor-logger)
[![Codecov branch](https://img.shields.io/codecov/c/github/Cosium/annotation-processor-logger/master.svg)](https://codecov.io/gh/Cosium/annotation-processor-logger)
[![Maven Central Latest](https://img.shields.io/maven-central/v/com.cosium.logging/annotation-processor-logger.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.cosium.logging%22%20AND%20a%3A%22annotation-processor-logger%22)

# Annotation processor logger

A dedicated annotation processor SLF4J implementation which delegates logging to `javax.annotation.processing.Messager`.

### Usage

1. Add the dependency to your annotation processor project

```xml
<dependency>
  <groupId>com.cosium.logging</groupId>
  <artifactId>annotation-processor-logger</artifactId>
  <version>${annotation-processor-logger.version}</version>
</dependency>
```

2. Make your `javax.annotation.processing.Processor` extend `com.cosium.logging.annotation_processor.AbstractLoggingProcessor`

```java
public class MyProcessor extends AbstractLoggingProcessor {
    protected boolean doProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Make the world a better place
    }
}
```

3. Use SLF4J as usual

### Enable all logging levels

In the Maven project using your annotation processor, enable `showWarnings` in `maven-compiler-plugin`

```xml
<build>
  <plugins>
    <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-compiler-plugin</artifactId>
       <configuration>
         <showWarnings>true</showWarnings>
       </configuration>
    </plugin>
  </plugins>
</build>
```
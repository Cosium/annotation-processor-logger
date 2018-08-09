# Annotation processor logger

An SLF4J implementation for annotation processors which delegates logging to `javax.annotation.processing.Messager`.

### Usage

1. Add the dependency to your annotation processor project

```xml
<dependency>
  <groupId>com.cosium.logging</groupId>
  <artifactId>annotation-processor-logger</artifactId>
  <version>${annotation-processor-logger.version}</version>
</dependency>
```

2. Make your `javax.annotation.processing.Processor` extend `com.cosium.slf4j.annotation_processor.AbstractLoggingProcessor`

```java
public class MyProcessor extends AbstractLoggingProcessor{
    protected abstract boolean doProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        // Make the world a better place
    }
}
```

3. Use SLF4J as usual
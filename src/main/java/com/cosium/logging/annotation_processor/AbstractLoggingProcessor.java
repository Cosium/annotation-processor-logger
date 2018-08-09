package com.cosium.logging.annotation_processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Optional;
import java.util.Set;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public abstract class AbstractLoggingProcessor extends AbstractProcessor {

  private static final ThreadLocal<Messager> CURRENT_MESSAGER = new ThreadLocal<>();

  private Messager messager;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.messager = processingEnv.getMessager();
  }

  public static Optional<Messager> getCurrentMessager() {
    return Optional.ofNullable(CURRENT_MESSAGER.get());
  }

  @Override
  public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    CURRENT_MESSAGER.set(messager);
    try {
      return doProcess(annotations, roundEnv);
    } finally {
      CURRENT_MESSAGER.set(null);
    }
  }

  protected abstract boolean doProcess(
      Set<? extends TypeElement> annotations, RoundEnvironment roundEnv);
}

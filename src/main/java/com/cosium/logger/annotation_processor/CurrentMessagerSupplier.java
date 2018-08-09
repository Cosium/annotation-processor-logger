package com.cosium.logger.annotation_processor;

import javax.annotation.processing.Messager;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class CurrentMessagerSupplier implements Supplier<Optional<Messager>> {
  @Override
  public Optional<Messager> get() {
    return AbstractLoggingProcessor.getCurrentMessager();
  }
}

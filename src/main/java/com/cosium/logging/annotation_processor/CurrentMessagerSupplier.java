package com.cosium.logging.annotation_processor;

import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.processing.Messager;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
class CurrentMessagerSupplier implements Supplier<Optional<Messager>> {
  @Override
  public Optional<Messager> get() {
    return AbstractLoggingProcessor.getCurrentMessager();
  }
}

package com.cosium.slf4j.annotation_processor;

import org.slf4j.ILoggerFactory;

import javax.annotation.processing.Messager;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerFactory implements ILoggerFactory {

  private final Supplier<Optional<Messager>> messagerSupplier;

  public LoggerFactory() {
    messagerSupplier = AbstractLoggingProcessor::getCurrentMessager;
  }

  @Override
  public org.slf4j.Logger getLogger(String name) {
    return new Logger(
        new MessagePrinter(messagerSupplier, new MessageBuilder(name)));
  }
}

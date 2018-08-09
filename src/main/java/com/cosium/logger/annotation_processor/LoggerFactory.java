package com.cosium.logger.annotation_processor;

import org.slf4j.ILoggerFactory;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerFactory implements ILoggerFactory {

  private final CurrentMessagerSupplier messagerSupplier;

  public LoggerFactory() {
    messagerSupplier = new CurrentMessagerSupplier();
  }

  @Override
  public org.slf4j.Logger getLogger(String name) {
    return new Logger(new MessagePrinter(messagerSupplier, new MessageBuilder(name)));
  }
}

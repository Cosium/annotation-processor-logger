package com.cosium.logging.annotation_processor;

import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.NOP_FallbackServiceProvider;

/**
 * @author Réda Housni Alaoui
 */
public class ServiceProvider extends NOP_FallbackServiceProvider {

  @Override
  public ILoggerFactory getLoggerFactory() {
    return new LoggerFactory();
  }
}

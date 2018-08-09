package org.slf4j.impl;

import com.cosium.logging.annotation_processor.LoggerFactory;
import org.slf4j.ILoggerFactory;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class StaticLoggerBinder {

  private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

  public static StaticLoggerBinder getSingleton() {
    return SINGLETON;
  }

  /**
   * Declare the version of the SLF4J API this implementation is compiled against. The value of this
   * field is modified with each major release.
   */
  // to avoid constant folding by the compiler, this field must *not* be final
  public static String REQUESTED_API_VERSION = "1.6.99"; // !final

  private StaticLoggerBinder() {}

  public ILoggerFactory getLoggerFactory() {
    return new LoggerFactory();
  }

  public String getLoggerFactoryClassStr() {
    return LoggerFactory.class.toString();
  }
}

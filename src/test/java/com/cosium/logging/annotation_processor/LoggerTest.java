package com.cosium.logging.annotation_processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import javax.tools.Diagnostic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerTest {

  private static final String MSG = "msg";
  private static final String FORMAT = "format";
  private static final String ARG = "arg";
  private static final String ARG1 = "arg1";
  private static final String ARG2 = "arg2";
  private static final Object[] ARGUMENTS = new Object[] {ARG1, ARG2};
  private static final Throwable THROWABLE = mock(Throwable.class);

  private MessagePrinter messagePrinter;
  private Logger tested;

  @BeforeEach
  public void before() {
    messagePrinter = mock(MessagePrinter.class);
    tested = new Logger(messagePrinter);
  }

  @Test
  @DisplayName("All levels enabled")
  public void test1() {
    assertThat(tested.isTraceEnabled()).isTrue();
    assertThat(tested.isDebugEnabled()).isTrue();
    assertThat(tested.isInfoEnabled()).isTrue();
    assertThat(tested.isWarnEnabled()).isTrue();
    assertThat(tested.isErrorEnabled()).isTrue();
  }

  @Test
  @DisplayName("Trace")
  public void test2() {
    tested.trace(MSG);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, MSG);
    reset(messagePrinter);

    tested.trace(FORMAT, ARG);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARG);
    reset(messagePrinter);

    tested.trace(FORMAT, ARG1, ARG2);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARG1, ARG2);
    reset(messagePrinter);

    tested.trace(FORMAT, ARGUMENTS);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARGUMENTS);
    reset(messagePrinter);

    tested.trace(MSG, THROWABLE);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, MSG, THROWABLE);
    reset(messagePrinter);
  }

  @Test
  @DisplayName("Debug")
  public void test3() {
    tested.debug(MSG);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, MSG);
    reset(messagePrinter);

    tested.debug(FORMAT, ARG);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARG);
    reset(messagePrinter);

    tested.debug(FORMAT, ARG1, ARG2);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARG1, ARG2);
    reset(messagePrinter);

    tested.debug(FORMAT, ARGUMENTS);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, FORMAT, ARGUMENTS);
    reset(messagePrinter);

    tested.debug(MSG, THROWABLE);
    verify(messagePrinter).print(Diagnostic.Kind.OTHER, MSG, THROWABLE);
    reset(messagePrinter);
  }

  @Test
  @DisplayName("Info")
  public void test4() {
    tested.info(MSG);
    verify(messagePrinter).print(Diagnostic.Kind.NOTE, MSG);
    reset(messagePrinter);

    tested.info(FORMAT, ARG);
    verify(messagePrinter).print(Diagnostic.Kind.NOTE, FORMAT, ARG);
    reset(messagePrinter);

    tested.info(FORMAT, ARG1, ARG2);
    verify(messagePrinter).print(Diagnostic.Kind.NOTE, FORMAT, ARG1, ARG2);
    reset(messagePrinter);

    tested.info(FORMAT, ARGUMENTS);
    verify(messagePrinter).print(Diagnostic.Kind.NOTE, FORMAT, ARGUMENTS);
    reset(messagePrinter);

    tested.info(MSG, THROWABLE);
    verify(messagePrinter).print(Diagnostic.Kind.NOTE, MSG, THROWABLE);
    reset(messagePrinter);
  }

  @Test
  @DisplayName("Warn")
  public void test5() {
    tested.warn(MSG);
    verify(messagePrinter).print(Diagnostic.Kind.WARNING, MSG);
    reset(messagePrinter);

    tested.warn(FORMAT, ARG);
    verify(messagePrinter).print(Diagnostic.Kind.WARNING, FORMAT, ARG);
    reset(messagePrinter);

    tested.warn(FORMAT, ARG1, ARG2);
    verify(messagePrinter).print(Diagnostic.Kind.WARNING, FORMAT, ARG1, ARG2);
    reset(messagePrinter);

    tested.warn(FORMAT, ARGUMENTS);
    verify(messagePrinter).print(Diagnostic.Kind.WARNING, FORMAT, ARGUMENTS);
    reset(messagePrinter);

    tested.warn(MSG, THROWABLE);
    verify(messagePrinter).print(Diagnostic.Kind.WARNING, MSG, THROWABLE);
    reset(messagePrinter);
  }

  @Test
  @DisplayName("Error")
  public void test6() {
    tested.error(MSG);
    verify(messagePrinter).print(Diagnostic.Kind.ERROR, MSG);
    reset(messagePrinter);

    tested.error(FORMAT, ARG);
    verify(messagePrinter).print(Diagnostic.Kind.ERROR, FORMAT, ARG);
    reset(messagePrinter);

    tested.error(FORMAT, ARG1, ARG2);
    verify(messagePrinter).print(Diagnostic.Kind.ERROR, FORMAT, ARG1, ARG2);
    reset(messagePrinter);

    tested.error(FORMAT, ARGUMENTS);
    verify(messagePrinter).print(Diagnostic.Kind.ERROR, FORMAT, ARGUMENTS);
    reset(messagePrinter);

    tested.error(MSG, THROWABLE);
    verify(messagePrinter).print(Diagnostic.Kind.ERROR, MSG, THROWABLE);
    reset(messagePrinter);
  }
}

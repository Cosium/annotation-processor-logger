package com.cosium.logging.annotation_processor;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class MessagePrinterTest {

  private static final String FORMAT = "hello";
  private static final String ARGUMENT = "john";
  private static final String MESSAGE = "message";

  private Messager currentMessager;
  private MessageBuilder messageBuilder;

  private MessagePrinter tested;

  @BeforeEach
  public void before() {
    currentMessager = mock(Messager.class);
    messageBuilder = mock(MessageBuilder.class);
    when(messageBuilder.build(FORMAT, ARGUMENT)).thenReturn(MESSAGE);
    CurrentMessagerSupplier messagerSupplier = mock(CurrentMessagerSupplier.class);
    when(messagerSupplier.get()).thenAnswer(invocationOnMock -> ofNullable(currentMessager));
    tested = new MessagePrinter(messagerSupplier, messageBuilder);
  }

  @Test
  @DisplayName("Given no current messager when print then it does nothing")
  public void test1() {
    currentMessager = null;
    tested.print(Diagnostic.Kind.OTHER, "hello", "john");
    verifyNoMoreInteractions(messageBuilder);
  }

  @Test
  @DisplayName("When print then it print message using the messager")
  public void test2() {
    tested.print(Diagnostic.Kind.OTHER, FORMAT, ARGUMENT);
    verify(currentMessager).printMessage(Diagnostic.Kind.OTHER, MESSAGE);
  }
}

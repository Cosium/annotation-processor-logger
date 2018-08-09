package com.cosium.slf4j.annotation_processor;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.*;

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

  @Before
  public void before() {
    currentMessager = mock(Messager.class);
    messageBuilder = mock(MessageBuilder.class);
    when(messageBuilder.build(FORMAT, ARGUMENT)).thenReturn(MESSAGE);
    tested = new MessagePrinter(() -> ofNullable(currentMessager), messageBuilder);
  }

  @Test
  public void GIVEN_no_current_messager_WHEN_print_THEN_it_does_nothing() {
    currentMessager = null;
    tested.print(Diagnostic.Kind.OTHER, "hello", "john");
    verifyNoMoreInteractions(messageBuilder);
  }

  @Test
  public void WHEN_print_THEN_it_print_message_using_the_messager() {
    tested.print(Diagnostic.Kind.OTHER, FORMAT, ARGUMENT);
    verify(currentMessager).printMessage(Diagnostic.Kind.OTHER, MESSAGE);
  }
}

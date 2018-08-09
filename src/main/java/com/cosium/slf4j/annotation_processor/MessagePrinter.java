package com.cosium.slf4j.annotation_processor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class MessagePrinter {

  private final Supplier<Optional<Messager>> messagerSupplier;
  private final MessageBuilder messageBuilder;

  public MessagePrinter(
      Supplier<Optional<Messager>> messagerSupplier, MessageBuilder messageBuilder) {
    this.messagerSupplier = messagerSupplier;
    this.messageBuilder = messageBuilder;
  }

  public void print(Diagnostic.Kind kind, String format, Object... arguments) {
    messagerSupplier
        .get()
        .ifPresent(
            messager -> messager.printMessage(kind, messageBuilder.build(format, arguments)));
  }
}

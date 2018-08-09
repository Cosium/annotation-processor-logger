package com.cosium.logging.annotation_processor;

import javax.tools.Diagnostic;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class MessagePrinter {

  private final CurrentMessagerSupplier messagerSupplier;
  private final MessageBuilder messageBuilder;

  public MessagePrinter(CurrentMessagerSupplier messagerSupplier, MessageBuilder messageBuilder) {
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

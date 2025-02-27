package com.cosium.logging.annotation_processor;

import static java.util.Objects.requireNonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
class MessageBuilder {

  private final String producerName;

  public MessageBuilder(String producerName) {
    this.producerName = requireNonNull(producerName);
  }

  public String build(String format, Object... arguments) {
    FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);

    String fullMessage = producerName + " - ";
    String message = formattingTuple.getMessage();
    if (message != null) {
      fullMessage += message;
    }
    Throwable throwable = formattingTuple.getThrowable();
    if (throwable != null) {
      StringWriter stackTraceWriter = new StringWriter();
      throwable.printStackTrace(new PrintWriter(stackTraceWriter));
      fullMessage += "\n" + stackTraceWriter.toString();
    }
    return fullMessage;
  }
}

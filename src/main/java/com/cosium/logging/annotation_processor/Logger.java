package com.cosium.logging.annotation_processor;

import org.slf4j.helpers.MarkerIgnoringBase;

import javax.tools.Diagnostic;

import static java.util.Objects.requireNonNull;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class Logger extends MarkerIgnoringBase {

  private final MessagePrinter messagePrinter;

  public Logger(MessagePrinter messagePrinter) {
    this.messagePrinter = requireNonNull(messagePrinter);
  }

  @Override
  public boolean isTraceEnabled() {
    return true;
  }

  @Override
  public void trace(String msg) {
    doOther(msg);
  }

  @Override
  public void trace(String format, Object arg) {
    doOther(format, arg);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    doOther(format, arg1, arg2);
  }

  @Override
  public void trace(String format, Object... arguments) {
    doOther(format, arguments);
  }

  @Override
  public void trace(String msg, Throwable t) {
    doOther(msg, t);
  }

  @Override
  public boolean isDebugEnabled() {
    return true;
  }

  @Override
  public void debug(String msg) {
    doOther(msg);
  }

  @Override
  public void debug(String format, Object arg) {
    doOther(format, arg);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    doOther(format, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... arguments) {
    doOther(format, arguments);
  }

  @Override
  public void debug(String msg, Throwable t) {
    doOther(msg, t);
  }

  private void doOther(String format, Object... arguments) {
    messagePrinter.print(Diagnostic.Kind.OTHER, format, arguments);
  }

  @Override
  public boolean isInfoEnabled() {
    return true;
  }

  @Override
  public void info(String msg) {
    doNote(msg);
  }

  @Override
  public void info(String format, Object arg) {
    doNote(format, arg);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    doNote(format, arg1, arg2);
  }

  @Override
  public void info(String format, Object... arguments) {
    doNote(format, arguments);
  }

  @Override
  public void info(String msg, Throwable t) {
    doNote(msg, t);
  }

  private void doNote(String format, Object... arguments) {
    messagePrinter.print(Diagnostic.Kind.NOTE, format, arguments);
  }

  @Override
  public boolean isWarnEnabled() {
    return true;
  }

  @Override
  public void warn(String msg) {
    doWarn(msg);
  }

  @Override
  public void warn(String format, Object arg) {
    doWarn(format, arg);
  }

  @Override
  public void warn(String format, Object... arguments) {
    doWarn(format, arguments);
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    doWarn(format, arg1, arg2);
  }

  @Override
  public void warn(String msg, Throwable t) {
    doWarn(msg, t);
  }

  private void doWarn(String format, Object... arguments) {
    messagePrinter.print(Diagnostic.Kind.WARNING, format, arguments);
  }

  @Override
  public boolean isErrorEnabled() {
    return true;
  }

  @Override
  public void error(String msg) {
    doError(msg);
  }

  @Override
  public void error(String format, Object arg) {
    doError(format, arg);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    doError(format, arg1, arg2);
  }

  @Override
  public void error(String format, Object... arguments) {
    doError(format, arguments);
  }

  @Override
  public void error(String msg, Throwable t) {
    doError(msg, t);
  }

  private void doError(String format, Object... arguments) {
    messagePrinter.print(Diagnostic.Kind.ERROR, format, arguments);
  }
}

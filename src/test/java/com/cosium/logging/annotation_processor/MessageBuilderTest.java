package com.cosium.logging.annotation_processor;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class MessageBuilderTest {

  private static final String PRODUCER_NAME = "producer";
  private static final String STACK_TRACE = "stack-trace";

  private MessageBuilder tested;

  @Before
  public void before() {
    tested = new MessageBuilder(PRODUCER_NAME);
  }

  @Test
  public void testInterpolation() {
    assertThat(tested.build("Hello {}", "John")).isEqualTo(PRODUCER_NAME + " - Hello John");
  }

  @Test
  public void testNull() {
    assertThat(tested.build(null)).isEqualTo(PRODUCER_NAME + " - ");
  }

  @Test
  public void testException() {
    StubbedThrowable throwable = new StubbedThrowable(STACK_TRACE);
    assertThat(tested.build(null, throwable)).isEqualTo(PRODUCER_NAME + " - \n" + STACK_TRACE);
  }

  @Test
  public void testInterpolationAndException() {
    StubbedThrowable throwable = new StubbedThrowable(STACK_TRACE);
    assertThat(tested.build("Hello {}", "John", throwable))
        .isEqualTo(PRODUCER_NAME + " - Hello John\n" + STACK_TRACE);
  }

  private class StubbedThrowable extends Throwable {

    private final String stackTrace;

    private StubbedThrowable(String stackTrace) {
      this.stackTrace = stackTrace;
    }

    @Override
    public void printStackTrace(PrintWriter s) {
      s.write(stackTrace);
    }
  }
}

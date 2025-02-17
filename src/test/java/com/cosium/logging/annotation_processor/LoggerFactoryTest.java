package com.cosium.logging.annotation_processor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerFactoryTest {

  private LoggerFactory tested;

  @BeforeEach
  public void before() {
    tested = new LoggerFactory();
  }

  @Test
  public void test() {
    assertThat(tested.getLogger("john")).isInstanceOf(Logger.class);
  }
}

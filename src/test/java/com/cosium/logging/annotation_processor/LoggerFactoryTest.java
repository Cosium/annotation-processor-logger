package com.cosium.logging.annotation_processor;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerFactoryTest {

  private LoggerFactory tested;

  @Before
  public void before() {
    tested = new LoggerFactory();
  }

  @Test
  public void test() {
    assertThat(tested.getLogger("john")).isInstanceOf(Logger.class);
  }
}

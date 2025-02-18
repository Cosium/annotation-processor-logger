package com.cosium.logging.annotation_processor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class LoggerFactoryTest {

  @Test
  public void test() {
    assertThat(org.slf4j.LoggerFactory.getLogger("john")).isInstanceOf(Logger.class);
  }
}

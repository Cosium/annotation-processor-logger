package com.cosium.logging.annotation_processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created on 09/08/18.
 *
 * @author Reda.Housni-Alaoui
 */
public class AbstractLoggingProcessorTest {

  private ProcessingEnvironment processingEnvironment;
  private Messager messager;
  private Runnable processRunnable = () -> {};
  private Processor tested;

  @BeforeEach
  public void before() {
    processingEnvironment = mock(ProcessingEnvironment.class);
    messager = mock(Messager.class);
    when(processingEnvironment.getMessager()).thenReturn(messager);
    tested = new Processor();
  }

  @Test
  @DisplayName("Given no init neither current round when getCurrentMessager then empty is returned")
  public void test1() {
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  @DisplayName(
      "Given initialized and stopped processor when getCurrentMessager then empty is returned")
  public void test2() {
    tested.init(processingEnvironment);
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  @DisplayName(
      "Given initialized and running processor when getCurrentMessager then messager is returned")
  public void test3() {
    tested.init(processingEnvironment);
    AtomicReference<Messager> retrievedMessager = new AtomicReference<>();
    processRunnable =
        () -> retrievedMessager.set(AbstractLoggingProcessor.getCurrentMessager().orElse(null));
    tested.process(null, null);
    assertThat(retrievedMessager.get()).isSameAs(messager);
  }

  @Test
  @DisplayName("Given initialized and run processor when getCurrentMessager then empty is returned")
  public void test4() {
    tested.init(processingEnvironment);
    tested.process(null, null);
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  @DisplayName(
      "Given initialized and lastly failed round processor when getCurrentMessager then empty is returned")
  public void test5() {
    tested.init(processingEnvironment);
    tested.fail = true;
    assertThatThrownBy(() -> tested.process(null, null)).isInstanceOf(ExpectedFailure.class);
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  private class Processor extends AbstractLoggingProcessor {

    private boolean fail;

    @Override
    protected boolean doProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      if (fail) {
        throw new ExpectedFailure();
      }
      processRunnable.run();
      return false;
    }
  }

  private static class ExpectedFailure extends RuntimeException {}
}

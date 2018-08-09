package com.cosium.logging.annotation_processor;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

  @Before
  public void before() {
    processingEnvironment = mock(ProcessingEnvironment.class);
    messager = mock(Messager.class);
    when(processingEnvironment.getMessager()).thenReturn(messager);
    tested = new Processor();
  }

  @Test
  public void GIVEN_no_init_neither_current_round_WHEN_getmessager_THEN_empty_is_returned() {
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  public void GIVEN_initialized_and_stopped_processor_WHEN_getmessager_THEN_empty_is_returned() {
    tested.init(processingEnvironment);
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  public void GIVEN_initialized_and_running_processor_WHEN_getmessager_THEN_messager_is_returned() {
    tested.init(processingEnvironment);
    AtomicReference<Messager> retrievedMessager = new AtomicReference<>();
    processRunnable =
        () -> retrievedMessager.set(AbstractLoggingProcessor.getCurrentMessager().orElse(null));
    tested.process(null, null);
    assertThat(retrievedMessager.get()).isSameAs(messager);
  }

  @Test
  public void GIVEN_initialized_and_runned_processor_WHEN_getmessager_THEN_empty_is_returned() {
    tested.init(processingEnvironment);
    tested.process(null, null);
    assertThat(AbstractLoggingProcessor.getCurrentMessager()).isEmpty();
  }

  @Test
  public void
      GIVEN_initialized_and_lastly_failed_round_processor_WHEN_getmessager_THEN_empty_is_returned() {
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

  private class ExpectedFailure extends RuntimeException {}
}

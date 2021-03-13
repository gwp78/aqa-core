package ru.gwp.steps.assertion;

import io.qameta.allure.Step;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

/** Utility class with base assertion steps. Integrated with Allure. */
public final class BaseAssertionSteps {

  private BaseAssertionSteps() {}

  /**
   * Asserts that provided list is not null and not empty.
   *
   * @param subject asserted subject name. Used for reporting.
   * @param actual asserted value.
   */
  @Step("{subject} shouldn't be empty")
  public static void assertNotEmpty(String subject, List<?> actual) {
    assertThat(actual).withFailMessage(format("%s is null", subject)).isNotNull();
    assertThat(actual).withFailMessage(format("%s is empty", subject)).isNotEmpty();
  }
}

package ru.gwp.error;

/**
 * Common class for errors that occur during the execution of test scripts not related to
 * assertions. Subclass of a @{@link Error}.
 */
public final class AutoTestError extends Error {

  public AutoTestError(String message) {
    super(message);
  }

  public AutoTestError(String message, Throwable cause) {
    super(message, cause);
  }

  public AutoTestError(Throwable cause) {
    super(cause);
  }
}

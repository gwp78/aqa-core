package ru.gwp.constants;

import lombok.Getter;
import ru.gwp.error.AutoTestError;

import static java.lang.String.format;
import static java.util.Arrays.stream;

/** Stores available debug modes. */
public enum LogLevel {
  /** DEBUG mode shows detail logs. */
  DEBUG("debug"),
  /** PROD mode hides detail logs. */
  NONE("none");

  @Getter private final String name;

  LogLevel(String name) {
    this.name = name;
  }

  /**
   * Returns debug mode by provided mode.
   *
   * @param mode mode as {@link String}.
   * @return debug mode.
   */
  public static LogLevel fromString(String mode) {
    return stream(LogLevel.values())
        .filter(_mode -> _mode.name.equals(mode))
        .findFirst()
        .orElseThrow(() -> new AutoTestError(format("There is no debug mode '%s'", mode)));
  }
}

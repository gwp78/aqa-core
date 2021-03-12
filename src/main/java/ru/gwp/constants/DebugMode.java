package ru.gwp.constants;

import lombok.Getter;

/** Stores available debug modes. */
public enum DebugMode {
  /** DEBUG mode shows detail logs */
  DEBUG("debug"),
  /** PROD mode hides detail logs */
  PROD("prod");

  @Getter private final String name;

  DebugMode(String name) {
    this.name = name;
  }
}

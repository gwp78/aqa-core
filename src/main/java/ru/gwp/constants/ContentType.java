package ru.gwp.constants;

import lombok.Getter;

/** Stores available REST request content types. */
public enum ContentType {
  JSON("application/json");

  @Getter private final String name;

  ContentType(String name) {
    this.name = name;
  }
}

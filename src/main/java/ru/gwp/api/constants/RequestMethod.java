package ru.gwp.api.constants;

import lombok.Getter;

/** Stores available HTTP request methods. */
public enum RequestMethod {
  POST("POST"),
  PUT("PUT"),
  GET("GET"),
  DELETE("DELETE");

  @Getter private final String name;

  RequestMethod(String name) {
    this.name = name;
  }
}

package ru.gwp.api.rest;

import org.hamcrest.Matcher;

/** Provides functionality to work with REST response. */
public interface RestResponse {

  /**
   * Validates REST response status code.
   *
   * @param statusCode expected status code
   * @return REST response
   */
  RestResponse expectStatusCode(int statusCode);

  /**
   * Validates that REST response matches to JSON schema.
   *
   * @param jsonSchemaPath path to JSON schema.
   * @return REST response
   */
  RestResponse expectMatchesJsonSchema(String jsonSchemaPath);

  /**
   * Validates that REST response body value with specific path matches to provided {@link Matcher}.
   *
   * @param path body JSON path.
   * @return REST response
   */
  RestResponse expectPathValueMatchesTo(String path, Matcher<?> matcher);

  /**
   * Deserializes REST response body to instance of provided class.
   *
   * @param cls class for deserialization.
   * @return new instance of provided class
   */
  <T> T extractAs(Class<T> cls);
}

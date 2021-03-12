package ru.gwp.api;

import ru.gwp.api.rest.RestRequest;
import ru.gwp.constants.ContentType;

import static ru.gwp.constants.ContentType.JSON;

/** Provides functionality to work with REST API. */
public interface BaseRestApi {

  /** Returns REST request content type. Default value is {@link ContentType#JSON}. */
  default ContentType getContentType() {
    return JSON;
  }

  /** Creates preconfigured {@link RestRequest}. */
  RestRequest request();
}

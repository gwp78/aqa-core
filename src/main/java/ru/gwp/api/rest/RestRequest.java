package ru.gwp.api.rest;

import ru.gwp.constants.RequestMethod;

/** Provides functionality to work with REST request. */
public interface RestRequest {

  /**
   * Sets REST request content type
   *
   * @param contentType REST request content type as {@link String}. TEXT: text/plain; JSON:
   *     application/json, application/javascript, text/javascript, text/json; XML: application/xml,
   *     text/xml, application/xhtml+xml; HTML: text/html; URLENC:
   *     application/x-www-form-urlencoded; BINARY: application/octet-stream.
   * @return REST request.
   */
  RestRequest setContentType(String contentType);

  /**
   * Adds REST request header.
   *
   * @param headerName header name as {@link String}.
   * @param headerValue header value as {@link String}.
   * @return REST request.
   */
  RestRequest addHeader(String headerName, String headerValue);

  /**
   * Sets REST request body.
   *
   * @param pojo the instance of POJO class.
   * @return REST request.
   */
  RestRequest setBody(Object pojo);

  /**
   * Sets base URI for REST request.
   *
   * @param uri base URI as {@link String}.
   * @return REST request.
   */
  RestRequest setBaseUri(String uri);

  /**
   * Sets base PATH for REST request.
   *
   * @param path base PATH as {@link String}.
   * @return REST request.
   */
  RestRequest setBasePath(String path);

  /**
   * Adds query param for REST request.
   *
   * @param parameterName parameter name as {@link String}.
   * @param parameterValues parameter values.
   * @return REST request.
   */
  RestRequest addQueryParam(String parameterName, Object... parameterValues);

  /**
   * Executes REST request.
   *
   * @param method request method. Look {@link RequestMethod}.
   * @return REST response. Look {@link RestResponse}.
   */
  RestResponse execute(RequestMethod method);

  /**
   * Executes REST request with specific path.
   *
   * @param method request method. Look {@link RequestMethod}.
   * @param path REST request path.
   * @return REST response. Look {@link RestResponse}.
   */
  RestResponse execute(RequestMethod method, String path);
}

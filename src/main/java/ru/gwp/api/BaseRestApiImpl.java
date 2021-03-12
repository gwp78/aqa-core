package ru.gwp.api;

import ru.gwp.api.rest.RestRequest;

/** Provides implementation of the base functionality to work with REST API. */
public abstract class BaseRestApiImpl implements BaseRestApi {

  private final RestRequest restRequest;
  private final String baseUri;

  protected BaseRestApiImpl(String baseUri, RestRequest restRequest) {
    this.baseUri = baseUri;
    this.restRequest = restRequest;
  }

  /**
   * Creates preconfigured {@link RestRequest} with content type {@link
   * BaseRestApi#getContentType()}.
   */
  public RestRequest request() {
    return restRequest.setContentType(getContentType()).setBaseUri(baseUri);
  }
}

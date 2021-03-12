package ru.gwp.api.rest.restAssured;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.restassured.builder.RequestSpecBuilder;
import lombok.Getter;
import ru.gwp.api.rest.RestRequest;
import ru.gwp.api.rest.RestResponse;
import ru.gwp.constants.ContentType;
import ru.gwp.constants.DebugMode;
import ru.gwp.constants.RequestMethod;

import static com.google.common.base.Preconditions.checkNotNull;
import static lombok.AccessLevel.PACKAGE;
import static ru.gwp.constants.DebugMode.valueOf;

/**
 * Provides implementation of functionality to work with REST request. Wraps RestAssured methods
 * call.
 */
public final class RestAssuredRequest implements RestRequest {

  @Getter(PACKAGE)
  private final DebugMode debugMode;

  @Getter(PACKAGE)
  private final RequestSpecBuilder builder;

  @Getter(PACKAGE)
  private RequestMethod method;

  @Getter(PACKAGE)
  private String path;

  @Inject
  RestAssuredRequest(@Named("debugMode") String debugMode) {
    this.debugMode = valueOf(debugMode.toLowerCase());
    this.builder = new RequestSpecBuilder();
  }

  /**
   * Sets {@link RestAssuredRequest#builder} content type.
   *
   * @param contentType REST request content type as {@link String}.
   * @return REST request.
   */
  @Override
  public final RestRequest setContentType(ContentType contentType) {
    builder.setContentType(checkNotNull(contentType).getName());
    return this;
  }

  /**
   * Adds {@link RestAssuredRequest#builder} header.
   *
   * @param headerName header name as {@link String}.
   * @param headerValue header value as {@link String}.
   * @return REST request.
   */
  @Override
  public final RestRequest addHeader(String headerName, String headerValue) {
    builder.addHeader(checkNotNull(headerName), checkNotNull(headerValue));
    return this;
  }

  /**
   * Sets {@link RestAssuredRequest#builder} body.
   *
   * @param pojo the instance of POJO class.
   * @return REST request.
   */
  @Override
  public final RestRequest setBody(Object pojo) {
    builder.setBody(checkNotNull(pojo));
    return this;
  }

  /**
   * Sets base URI for {@link RestAssuredRequest#builder}.
   *
   * @param uri base URI as {@link String}.
   * @return REST request.
   */
  @Override
  public final RestRequest setBaseUri(String uri) {
    builder.setBaseUri(checkNotNull(uri));
    return this;
  }

  /**
   * Adds query param for {@link RestAssuredRequest#builder}.
   *
   * @param parameterName parameter name as {@link String}.
   * @param parameterValues parameter values.
   * @return REST request.
   */
  @Override
  public final RestRequest addQueryParam(String parameterName, Object... parameterValues) {
    builder.addQueryParam(checkNotNull(parameterName), parameterValues);
    return this;
  }

  /**
   * Executes REST request. Calls {@link RestAssuredRequest#execute(RequestMethod, String)}.
   *
   * @param method request method. Look {@link RequestMethod}.
   * @return REST response. Look {@link RestAssuredResponse}.
   */
  @Override
  public final RestResponse execute(RequestMethod method) {
    return execute(method, "");
  }

  /**
   * Sets values of {@link RestAssuredRequest#method} and {@link RestAssuredRequest#path}. And
   * creates new instance of {@link RestAssuredResponse} with {@link RestAssuredRequest} as
   * constructor argument.
   *
   * @param method request method. Look {@link RequestMethod}.
   * @param path REST request path.
   * @return REST response. Look {@link RestAssuredResponse}.
   */
  @Override
  public final RestResponse execute(RequestMethod method, String path) {
    this.method = method;
    this.path = path;
    return new RestAssuredResponse(this);
  }
}

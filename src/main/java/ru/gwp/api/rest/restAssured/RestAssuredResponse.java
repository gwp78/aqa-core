package ru.gwp.api.rest.restAssured;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import ru.gwp.api.rest.RestRequest;
import ru.gwp.api.rest.RestResponse;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static ru.gwp.constants.LogLevel.DEBUG;

/** Provides implementation of functionality to work with REST response. */
public final class RestAssuredResponse implements RestResponse {

  private final ValidatableResponse vResponse;

  RestAssuredResponse(RestAssuredRequest request) {
    vResponse = init(request);
  }

  /**
   * Validates REST response status code of {@link RestAssuredResponse#vResponse}.
   *
   * @param statusCode expected status code
   * @return REST response. Look {@link RestAssuredResponse}.
   */
  @Override
  public final RestResponse expectStatusCode(int statusCode) {
    vResponse.assertThat().statusCode(statusCode);
    return this;
  }

  /**
   * Validates that REST response body of {@link RestAssuredResponse#vResponse} matches to JSON
   * schema.
   *
   * @param path path to JSON schema.
   * @return REST response. Look {@link RestAssuredResponse}.
   */
  @Override
  public final RestResponse expectMatchesJsonSchema(String path) {
    vResponse.assertThat().body(matchesJsonSchemaInClasspath(path));
    return this;
  }

  /**
   * Validates that REST response body of {@link RestAssuredResponse#vResponse} value with specific
   * path matches to provided {@link Matcher}.
   *
   * @param path body JSON path.
   * @return REST response. Look {@link RestAssuredResponse}.
   */
  @Override
  public RestResponse expectPathValueMatchesTo(String path, Matcher<?> matcher) {
    vResponse.assertThat().body(path, matcher);
    return this;
  }

  /**
   * Deserializes REST response body of {@link RestAssuredResponse#vResponse} to instance of
   * provided class.
   *
   * @param cls class for deserialization.
   * @return new instance of provided class.
   */
  @Override
  public final <T> T extractAs(Class<T> cls) {
    return vResponse.extract().as(cls);
  }

  private static ValidatableResponse init(RestAssuredRequest request) {
    RequestSpecification specification = request.getBuilder().build();

    if (DEBUG.equals(request.getLogLevel())) {
      specification = specification.log().all();
    }

    Response response = null;
    switch (request.getMethod()) {
      case POST:
        response = given().spec(specification).post(request.getPath());
        break;
      case PUT:
        response = given().spec(specification).put(request.getPath());
        break;
      case GET:
        response = given().spec(specification).get(request.getPath());
        break;
      case DELETE:
        response = given().spec(specification).delete(request.getPath());
        break;
    }

    ValidatableResponse vResponse = checkNotNull(response).then();
    if (DEBUG.equals(request.getLogLevel())) {
      vResponse = vResponse.log().all();
    }

    return vResponse;
  }
}

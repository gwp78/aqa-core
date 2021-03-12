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
import static ru.gwp.constants.DebugMode.DEBUG;

public final class RestAssuredResponse implements RestResponse {

  private final ValidatableResponse vResponse;

  RestAssuredResponse(RestRequest request) {
    vResponse = init((RestAssuredRequest) request);
  }

  @Override
  public final RestResponse expectStatusCode(int statusCode) {
    vResponse.assertThat().statusCode(statusCode);
    return this;
  }

  @Override
  public final RestResponse expectMatchesJsonSchema(String path) {
    vResponse.assertThat().body(matchesJsonSchemaInClasspath(path));
    return this;
  }

  @Override
  public RestResponse expectPathValueMatchesTo(String path, Matcher<?> matcher) {
    vResponse.assertThat().body(path, matcher);
    return this;
  }

  @Override
  public final <T> T extractAs(Class<T> cls) {
    return vResponse.extract().as(cls);
  }

  private static ValidatableResponse init(RestAssuredRequest request) {
    RequestSpecification specification = request.getBuilder().build();

    if (DEBUG.equals(request.getDebugMode())) {
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
    if (DEBUG.equals(request.getDebugMode())) {
      vResponse = vResponse.log().all();
    }

    return vResponse;
  }
}

package ru.gwp.junit;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.restassured.RestAssured.filters;

/** Provides BeforeAll callback for JUnit5. Connects RestAssured with Allure Reporter. */
public final class AllureRestAssuredBeforeAll implements BeforeAllCallback {

  private static final AtomicBoolean IS_FILTER_ADDED = new AtomicBoolean(false);

  /**
   * Adds {@link AllureRestAssured} filter for JUnit5.
   *
   * @param context {@link ExtensionContext}
   */
  @Override
  public void beforeAll(ExtensionContext context) {
    if (IS_FILTER_ADDED.compareAndSet(/* expectedValue= */ false, /* newValue= */ true)) {
      filters(new AllureRestAssured());
    }
  }
}

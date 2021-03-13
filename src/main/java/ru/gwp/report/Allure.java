package ru.gwp.report;

import static io.qameta.allure.util.ResultsUtils.createTmsLink;

/** Utility class to extend Allure reports. */
public final class Allure {

  private static final String TEST_CASE = "Test case";

  private Allure() {}

  /**
   * Adds tms link with default name {@link Allure#TEST_CASE}.
   *
   * @param testCaseId test case id in TestRail.
   */
  public static void tms(String testCaseId) {
    tms(TEST_CASE, createTmsLink(testCaseId).getUrl());
  }

  /**
   * Adds tms link with provided name.
   *
   * @param testCaseId test case id in TestRail.
   */
  public static void tms(String linkName, String testCaseId) {
    io.qameta.allure.Allure.tms(linkName, createTmsLink(testCaseId).getUrl());
  }
}

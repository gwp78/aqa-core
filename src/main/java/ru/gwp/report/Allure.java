package ru.gwp.report;

import static io.qameta.allure.Allure.tms;
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
  public static void link(String testCaseId) {
    link(TEST_CASE, createTmsLink(testCaseId).getUrl());
  }

  /**
   * Adds tms link with provided name.
   *
   * @param testCaseId test case id in TestRail.
   */
  public static void link(String linkName, String testCaseId) {
    tms(linkName, createTmsLink(testCaseId).getUrl());
  }
}

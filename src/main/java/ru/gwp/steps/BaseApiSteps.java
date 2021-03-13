package ru.gwp.steps;

/** Provides base steps to work with REST API. */
public interface BaseApiSteps {

  /**
   * Saves a provided value in storage with a provided key.
   *
   * @param key key to store with in storage.
   * @param value value to store in storage.
   * @return provided value.
   */
  <T> T saveValue(String key, T value);

  /**
   * Extracts from storage value by provided key and casts it to provided class.
   *
   * @param key key to extract from storage.
   * @param cls expected class of the value stored in storage with provided key.
   * @return extracted from storage value.
   */
  <T> T getValue(String key, Class<T> cls);
}

package ru.gwp.steps;

import ru.gwp.error.AutoTestError;

import java.util.Map;

import static java.lang.String.format;

/** Base class for REST API steps. Provides implementation of base steps to work with REST API. */
public class BaseApiStepsImpl implements BaseApiSteps {

  private final ThreadLocal<Map<String, Object>> storage;

  protected BaseApiStepsImpl(ThreadLocal<Map<String, Object>> storage) {
    this.storage = storage;
  }

  /**
   * Saves a provided value in {@link BaseApiStepsImpl#storage} with a provided key.
   *
   * @param key key to store with in storage.
   * @param value value to store in storage.
   * @return provided value.
   */
  @Override
  public final <T> T saveValue(String key, T value) {
    storage.get().put(key, value);
    return value;
  }

  /**
   * Extracts from {@link BaseApiStepsImpl#storage} value by provided key and casts it to provided
   * class.
   *
   * @param key key to extract from storage.
   * @param cls expected class of the value stored in storage with provided key.
   * @return extracted from storage value.
   */
  @Override
  public final <T> T getValue(String key, Class<T> cls) {
    if (storage.get().containsKey(key)) {
      //TODO(gwp78) replace with Class#isAssignableFrom(Class)
      try {
        return cls.cast(storage.get().get(key));
      } catch (ClassCastException exception) {
        throw new AutoTestError(exception);
      }
    } else {
      throw new AutoTestError(format("There is no value in storage with key '%s'", key));
    }
  }
}

package ru.gwp;

import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import ru.gwp.error.AutoTestError;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.inject.Guice.createInjector;
import static com.google.inject.name.Names.named;
import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import static java.lang.System.getenv;

/** Provides functionality for retrieving instances of classes using {@link Injector} */
public final class DIContainer {

  private final Injector injector;

  public DIContainer(AbstractModule module) {
    this.injector = createInjector(module);
  }

  /**
   * Creates bounded instance of class
   *
   * @param cls Class
   * @return Instance of given class
   */
  public <T> T getInstance(Class<T> cls) {
    return injector.getInstance(cls);
  }

  /**
   * Retrieves string value by provided key
   *
   * @param key named key
   * @return string value
   */
  public String getStringValueOf(String key) {
    String value = "";
    try {
      value = injector.getInstance(Key.get(String.class, named(key)));
      if (value.isBlank()) {
        value = checkNotNull(getenv(key));
      }
    } catch (ConfigurationException | NullPointerException exception) {
      throw new AutoTestError(
          format(
              "Configuration files and environment variables does not contain value with key '%s' or value is empty",
              key),
          exception);
    }
    return value;
  }

  /**
   * Retrieves integer value by provided key
   *
   * @param key named key
   * @return integer value
   */
  public Integer getIntegerValueOf(String key) {
    return valueOf(getStringValueOf(key));
  }
}

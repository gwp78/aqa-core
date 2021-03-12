package ru.gwp;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import ru.gwp.api.rest.RestRequest;
import ru.gwp.api.rest.restAssured.RestAssuredRequest;
import ru.gwp.error.AutoTestError;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.google.inject.name.Names.bindProperties;

/** Service class used by {@link com.google.inject.Guice} */
public final class CoreModule extends AbstractModule {

  private static final String PROPERTIES_FILE_PATH = "core.properties";

  @Override
  protected void configure() {
    initNames(binder(), this.getClass().getClassLoader(), PROPERTIES_FILE_PATH);
    bind(RestRequest.class).to(RestAssuredRequest.class);
  }

  /** Binds properties from the properties file located by the provided path */
  public static void initNames(Binder binder, ClassLoader loader, String path) {
    try (InputStream inputStream = loader.getResourceAsStream(path)) {
      Properties properties = new Properties();
      properties.load(inputStream);
      bindProperties(binder, properties);
    } catch (IOException exception) {
      throw new AutoTestError(exception);
    }
  }
}

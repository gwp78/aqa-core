package ru.gwp;

import com.google.inject.AbstractModule;
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
    initNames();
    bind(RestRequest.class).to(RestAssuredRequest.class);
  }

  private void initNames() {
    try (InputStream inputStream =
        this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
      Properties properties = new Properties();
      properties.load(inputStream);
      bindProperties(binder(), properties);
    } catch (IOException exception) {
      throw new AutoTestError(exception);
    }
  }
}

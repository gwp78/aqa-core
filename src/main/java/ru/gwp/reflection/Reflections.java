package ru.gwp.reflection;

import lombok.extern.slf4j.Slf4j;
import ru.gwp.error.AutoTestError;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.io.File.separator;
import static java.lang.Class.forName;
import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static java.nio.file.Files.walk;
import static java.nio.file.Path.of;
import static java.util.stream.Collectors.toSet;

@Slf4j
public final class Reflections {

  private static final String CLASS_POSTFIX = ".class";
  private static final String DOT = ".";

  private Reflections() {}

  public static Set<Class<?>> getClasses(List<String> packageNames) {
    ClassLoader loader = currentThread().getContextClassLoader();
    return getClasses(checkNotNull(packageNames), checkNotNull(loader));
  }

  private static Set<Class<?>> getClasses(List<String> packageNames, ClassLoader loader) {
    return packageNames.stream()
        .map(packageName -> getClasses(checkNotNull(packageName), loader))
        .flatMap(Collection::stream)
        .collect(toSet());
  }

  private static Set<Class<?>> getClasses(String packageName, ClassLoader loader) {
    try {
      return walk(getPackagePath(packageName, loader))
          .map(Path::toString)
          .filter(path -> path.endsWith(CLASS_POSTFIX))
          .map(
              path -> {
                path = path.replace(separator, DOT);
                path = path.substring(path.indexOf(packageName), path.lastIndexOf(CLASS_POSTFIX));
                try {
                  return forName(path);
                } catch (ClassNotFoundException exception) {
                  log.info(format("Unable to load class '%s'", exception.getMessage()));
                  return null;
                }
              })
          .filter(Objects::nonNull)
          .collect(toSet());
    } catch (IOException exception) {
      throw new AutoTestError(exception);
    }
  }

  private static Path getPackagePath(String packageName, ClassLoader loader) {
    try {
      Iterator<URL> iterator =
          loader.getResources(packageName.replace(DOT, separator)).asIterator();
      if (!iterator.hasNext()) {
        throw new AutoTestError(format("Unable to find package with name '%s'", packageName));
      }
      return of(iterator.next().toURI());
    } catch (IOException | URISyntaxException exception) {
      throw new AutoTestError(exception);
    }
  }
}

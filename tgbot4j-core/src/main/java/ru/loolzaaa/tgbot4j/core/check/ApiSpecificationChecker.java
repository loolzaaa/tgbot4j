package ru.loolzaaa.tgbot4j.core.check;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiSpecificationChecker {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String packageName = "ru.loolzaaa.tgbot4j.core.api";
        String scanPath = ApiSpecificationChecker.class.getResource("/ru/loolzaaa/tgbot4j/core/api").getPath();
        String filesPath = "./tgbot4j-core/src/main/java/ru/loolzaaa/tgbot4j/core/api";

        ApiSpecificationChecker apiSpecificationChecker = new ApiSpecificationChecker();
        apiSpecificationChecker.checkAllFieldsShouldHaveJsonPropertyAnnotations(packageName, scanPath);
        apiSpecificationChecker.checkAllClassesShouldHaveDataAndConstructorsAnnotations(filesPath);
        apiSpecificationChecker.checkAllJsonPropertiesValuesAreCorrect(packageName, scanPath);
    }

    public void checkAllClassesShouldHaveDataAndConstructorsAnnotations(String filesPath) throws IOException {
        List<Path> files = Files.walk(Paths.get(filesPath))
                .filter(Files::isRegularFile)
                .toList();
        List<String> invalidClasses = new ArrayList<>();
        for (Path path : files) {
            FileInputStream inputStream = new FileInputStream(path.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.lines().collect(Collectors.joining("\n"));
            if (!s.contains("@Data") || !s.contains("@NoArgsConstructor") || !s.contains("@AllArgsConstructor")) {
                String classPath = path.toString()
                        .substring(path.toString().indexOf("ru"))
                        .replace(".java", "")
                        .replaceAll("[\\\\/]", ".");
                invalidClasses.add(classPath);
            }
        }
        System.out.println(invalidClasses.isEmpty() ? "All classes have correct lombok annotations" : "Classes without lombok annotations: ");
        invalidClasses.forEach(System.out::println);
    }

    public void checkAllFieldsShouldHaveJsonPropertyAnnotations(String packageName, String scanPath) throws ClassNotFoundException {
        List<String> invalidFields = new ArrayList<>();
        for (Class<?> clazz : getAllClassesFromApiPackage(packageName, scanPath)) {
            if (!(clazz.getDeclaredFields().length == 0)) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAnnotationPresent(JsonProperty.class)) {
                        invalidFields.add(field.getDeclaringClass().getCanonicalName() + "." + field.getName());
                    }
                }
            }
        }
        System.out.println(invalidFields.isEmpty() ? "All fields have JsonProperty annotation" : "Fields without JsonProperty annotation: ");
        invalidFields.forEach(System.out::println);
    }

    public void checkAllJsonPropertiesValuesAreCorrect(String packageName, String scanPath) throws ClassNotFoundException {
        List<String> invalidPropertyValues = new ArrayList<>();
        for (Class<?> clazz : getAllClassesFromApiPackage(packageName, scanPath)) {
            if (!(clazz.getDeclaredFields().length == 0)) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(JsonProperty.class)) {
                        String value = field.getAnnotation(JsonProperty.class).value().toLowerCase();
                        String[] arr = value.split("_");
                        if (arr.length == 0) {
                            invalidPropertyValues.add(field.getDeclaringClass().getCanonicalName() + "." + field.getName());
                        } else {
                            String propertyName = "";
                            propertyName = propertyName.concat(arr[0]);
                            if (arr.length > 1) {
                                for (int i = 1; i < arr.length; i++) {
                                    propertyName = propertyName.concat((char)((int) arr[i].charAt(0) - 32) + arr[i].substring(1));
                                }
                            }
                            if (!propertyName.equals(field.getName())) {
                                invalidPropertyValues.add(field.getDeclaringClass().getCanonicalName() + "." + field.getName());
                            }
                        }
                    }
                }
            }
        }
        System.out.println(invalidPropertyValues.isEmpty() ? "All fields have correct JsonProperty value" : "Fields has incorrect JsonProperty value: ");
        invalidPropertyValues.forEach(System.out::println);
    }

    private Set<Class> getAllClassesFromApiPackage(String packageName, String scanPath) throws ClassNotFoundException {
        File[] files = new File(scanPath).listFiles();
        Set<Class> classes = new HashSet<>();
        if (files == null) return new HashSet<>();

        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".class")) {
                String className = f.getName().replaceAll("\\.class$", "");
                Class<?> cls = Class.forName(packageName + "." + className);
                classes.add(cls);
            } else {
                classes.addAll(getAllClassesFromApiPackage(packageName + "." + f.getName(), f.getPath()));
            }
        }

        return classes;
    }
}

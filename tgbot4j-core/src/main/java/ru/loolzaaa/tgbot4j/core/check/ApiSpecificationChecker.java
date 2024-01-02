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

import static java.lang.String.format;

public class ApiSpecificationChecker {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String typesPackageName = "ru.loolzaaa.tgbot4j.core.api.types";
        String methodsPackageName = "ru.loolzaaa.tgbot4j.core.api.methods";
        String typeScanPath = ApiSpecificationChecker.class.getResource("/ru/loolzaaa/tgbot4j/core/api/types").getPath();
        String methodScanPath = ApiSpecificationChecker.class.getResource("/ru/loolzaaa/tgbot4j/core/api/methods").getPath();
        String filesPath = "./tgbot4j-core/src/main/java/ru/loolzaaa/tgbot4j/core/api";

        ApiSpecificationChecker apiSpecificationChecker = new ApiSpecificationChecker();
        apiSpecificationChecker.checkAllClassesShouldHaveDataAndConstructorsAnnotations(filesPath);
        apiSpecificationChecker.checkAllFieldsShouldHaveJsonPropertyAnnotations(typesPackageName, typeScanPath, "Types");
        apiSpecificationChecker.checkAllFieldsShouldHaveJsonPropertyAnnotations(methodsPackageName, methodScanPath, "Methods");
        apiSpecificationChecker.checkAllJsonPropertiesValuesAreCorrect(typesPackageName, typeScanPath, "Types");
        apiSpecificationChecker.checkAllJsonPropertiesValuesAreCorrect(methodsPackageName, methodScanPath, "Methods");
    }

    public void checkAllClassesShouldHaveDataAndConstructorsAnnotations(String filesPath) throws IOException {
        List<Path> types = Files.walk(Paths.get(filesPath + "/types"))
                .filter(Files::isRegularFile)
                .toList();
        List<Path> methods = Files.walk(Paths.get(filesPath + "/methods"))
                .filter(Files::isRegularFile)
                .toList();
        List<Path> files = new ArrayList<>(types);
        files.addAll(methods);
        List<String> invalidClasses = new ArrayList<>();
        for (Path path : files) {
            // Class name exclusions
            if (path.getFileName().toString().contains("InputFile")) {
                continue;
            }
            FileInputStream inputStream = new FileInputStream(path.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.lines().collect(Collectors.joining("\n"));
            // Interface exclusions
            if (s.contains("public interface")) {
                continue;
            }
            if (!s.contains("@Data") || !s.contains("@NoArgsConstructor") || !s.contains("@AllArgsConstructor")) {
                String classPath = path.toString()
                        .substring(path.toString().indexOf("ru"))
                        .replace(".java", "")
                        .replaceAll("[\\\\/]", ".");
                invalidClasses.add(classPath);
            }
        }
        if (invalidClasses.isEmpty()) {
            System.out.println("#### All classes have correct lombok annotations :+1:");
        } else {
            System.out.println("#### Classes without lombok annotations :x::");
            invalidClasses.forEach(s -> System.out.println("- " + s));
        }
    }

    public void checkAllFieldsShouldHaveJsonPropertyAnnotations(String packageName, String scanPath, String scanType) throws ClassNotFoundException {
        List<String> invalidFields = new ArrayList<>();
        for (Class<?> clazz : getAllClassesFromApiPackage(packageName, scanPath)) {
            if (clazz.isAnonymousClass() || clazz.isAnnotationPresent(IgnoreCheck.class)) {
                continue;
            }
            if (!(clazz.getDeclaredFields().length == 0)) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAnnotationPresent(JsonProperty.class)) {
                        invalidFields.add(field.getDeclaringClass().getCanonicalName() + "." + field.getName());
                    }
                }
            }
        }
        System.out.println(invalidFields.isEmpty() ?
                format("#### All %s have JsonProperty annotation :+1:", scanType) :
                format("#### %s without JsonProperty annotation :x:: ", scanType));
        invalidFields.forEach(s -> System.out.println("- " + s));
    }

    public void checkAllJsonPropertiesValuesAreCorrect(String packageName, String scanPath, String scanType) throws ClassNotFoundException {
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
        System.out.println(invalidPropertyValues.isEmpty() ?
                format("#### All %s have correct JsonProperty value :+1:", scanType) :
                format("#### %s has incorrect JsonProperty value :x:: ", scanType));
        invalidPropertyValues.forEach(s -> System.out.println("- " + s));
    }

    private Set<Class<?>> getAllClassesFromApiPackage(String packageName, String scanPath) throws ClassNotFoundException {
        File[] files = new File(scanPath).listFiles();
        Set<Class<?>> classes = new HashSet<>();
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

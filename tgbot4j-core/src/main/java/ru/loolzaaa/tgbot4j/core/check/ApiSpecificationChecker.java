package ru.loolzaaa.tgbot4j.core.check;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * A set of methods for checking API objects and methods
 * for compliance with the Telegram Bot API specification.
 * <p>
 * Each method output must be formatted for Markdown style.
 */

public class ApiSpecificationChecker {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String typesPackageName = "ru.loolzaaa.tgbot4j.core.api.types";
        String methodsPackageName = "ru.loolzaaa.tgbot4j.core.api.methods";
        String typeScanPath = ApiSpecificationChecker.class.getResource("/ru/loolzaaa/tgbot4j/core/api/types").getPath();
        String methodScanPath = ApiSpecificationChecker.class.getResource("/ru/loolzaaa/tgbot4j/core/api/methods").getPath();
        String filesPath = "./tgbot4j-core/src/main/java/ru/loolzaaa/tgbot4j/core/api";

        ApiSpecificationChecker apiSpecificationChecker = new ApiSpecificationChecker();
        apiSpecificationChecker.checkAllClassesShouldHaveDataAndConstructorsAnnotations(filesPath);
        apiSpecificationChecker.compareFieldsWithDocumentation(typesPackageName, typeScanPath, ApiObject::addElementToEntityMap, "Types");
        apiSpecificationChecker.compareFieldsWithDocumentation(methodsPackageName, methodScanPath, ApiMethod::addElementToEntityMap, "Methods");
        apiSpecificationChecker.checkAllFieldsShouldHaveJsonPropertyAnnotations(typesPackageName, typeScanPath, "Types");
        apiSpecificationChecker.checkAllFieldsShouldHaveJsonPropertyAnnotations(methodsPackageName, methodScanPath, "Methods");
        apiSpecificationChecker.checkAllJsonPropertiesValuesAreCorrect(typesPackageName, typeScanPath, "Types");
        apiSpecificationChecker.checkAllJsonPropertiesValuesAreCorrect(methodsPackageName, methodScanPath, "Methods");
        apiSpecificationChecker.showAllClassesWithIgnoredCheckAnnotation(typesPackageName, typeScanPath, "Types");
        apiSpecificationChecker.showAllClassesWithIgnoredCheckAnnotation(methodsPackageName, methodScanPath, "Methods");
    }

    /**
     * This check shows whether all source classes have three
     * Lombok annotations: {@link lombok.NoArgsConstructor},
     * {@link lombok.AllArgsConstructor}, {@link lombok.Data}.
     * <p>
     * These annotations can be commented out,
     * but must be present in the code.
     *
     * @param filesPath Java sources path of API objects and methods
     * @throws IOException if file I/O errors
     */
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

    public void compareFieldsWithDocumentation(
            String packageName, String scanPath,
            BiConsumer<Map<String, ApiEntity>, Element> converter,
            String scanType
    ) throws ClassNotFoundException, IOException {
        List<String> incorrectFieldsCount = new ArrayList<>();
        List<String> invalidFields = new ArrayList<>();
        List<String> incorrectFieldType = new ArrayList<>();

        Document doc = Jsoup.connect("https://core.telegram.org/bots/api").get();
        Elements devPageContent = doc.selectFirst("#dev_page_content").children();

        for (Class<?> clazz : getAllClassesFromApiPackage(packageName, scanPath)) {
            if (clazz.isInterface() || clazz.isMemberClass() || clazz.isAnnotationPresent(IgnoreCheck.class)) {
                continue;
            }
            if (!(clazz.getDeclaredFields().length == 0)) {
                String className = clazz.getSimpleName();
                Map<String, ApiEntity> apiEntityMap = new HashMap<>();
                devPageContent.stream()
                        .filter(element -> element.normalName().equals("h4"))
                        .filter(element -> element.textNodes().stream().anyMatch(textNode -> textNode.text().equalsIgnoreCase(className)))
                        .findAny()
                        .orElseThrow(() -> new NoSuchElementException("No value for " + className))
                        .nextElementSiblings()
                        .select("table")
                        .first()
                        .select("tr")
                        .stream()
                        .skip(1)
                        .forEach(row -> converter.accept(apiEntityMap, row));
                if (apiEntityMap.size() != clazz.getDeclaredFields().length) {
                    incorrectFieldsCount.add(format("%s. Docs: %d. App: %d", className, apiEntityMap.size(), clazz.getDeclaredFields().length));
                }
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(JsonProperty.class)) {
                        String propertyValue = field.getAnnotation(JsonProperty.class).value();
                        if (!apiEntityMap.containsKey(propertyValue)) {
                            invalidFields.add(format("%s x-> %s", className, propertyValue));
                        } else {
                            ApiEntity apiEntity = apiEntityMap.get(propertyValue);
                            String fieldType = field.getType().getSimpleName();
                            String apiEntityJavaType = getJavaType(apiEntity.getType());
                            boolean equalTypes = apiEntityJavaType.equalsIgnoreCase(fieldType);
                            boolean apiTypeContainsJavaType = apiEntityJavaType.contains(fieldType.toLowerCase());
                            boolean complexType = apiEntityJavaType.equalsIgnoreCase("complex");
                            if (!equalTypes && !apiTypeContainsJavaType && !complexType) {
                                incorrectFieldType.add(format("%s -> %s. Docs: %s. App: %s", className, propertyValue, apiEntity.getType(), fieldType));
                            }
                            //TODO: implement required flag checks
//                            if (apiMethod.getJavaRequiredFlag() == null) {
//                                System.out.printf("%s -> %s. Unknown required flag: %s%n", className, propertyValue, apiMethod.required);
//                            } else if (apiMethod.getJavaRequiredFlag() && !field.isAnnotationPresent(Required.class)) {
//                                System.out.printf("%s -> %s need to be required%n", className, propertyValue);
//                            }
                        }
                    }
                }
            }
        }
        System.out.println(incorrectFieldsCount.isEmpty() ?
                format("#### All %s have correct field count :+1:", scanType) :
                format("#### %s without incorrect field count :x:: ", scanType));
        incorrectFieldsCount.forEach(s -> System.out.println("- " + s));

        System.out.println(invalidFields.isEmpty() ?
                format("#### All %s contains correct fields :+1:", scanType) :
                format("#### %s with incorrect fields :x:: ", scanType));
        invalidFields.forEach(s -> System.out.println("- " + s));

        System.out.println(incorrectFieldType.isEmpty() ?
                format("#### All %s contains correct field types :+1:", scanType) :
                format("#### %s with incorrect field types :x:: ", scanType));
        incorrectFieldType.forEach(s -> System.out.println("- " + s));
    }

    /**
     * This check shows whether all fields of API classes
     * of objects and methods contain annotation {@link JsonProperty}.
     * <p>
     * The annotation may be missing on anonymous classes,
     * as well as on those classes that are marked {@link IgnoreCheck}.
     * <p>
     * Scan type supported for API objects and methods separately.
     *
     * @param packageName name of package for class scanning
     * @param scanPath    common path contains package for scanning
     * @param scanType    type of class scanning
     * @throws ClassNotFoundException if class not found
     */
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

    /**
     * This check shows whether all fields of API classes
     * of objects and methods has correct name.
     * <p>
     * Name of every field must math value of {@link JsonProperty}
     * after replace all upper letters to lower case and '_' before each.
     *
     * @param packageName name of package for class scanning
     * @param scanPath    common path contains package for scanning
     * @param scanType    type of class scanning
     * @throws ClassNotFoundException if class not found
     */
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
                                    propertyName = propertyName.concat((char) ((int) arr[i].charAt(0) - 32) + arr[i].substring(1));
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

    public void showAllClassesWithIgnoredCheckAnnotation(String packageName, String scanPath, String scanType) throws ClassNotFoundException {
        List<String> ignoredClasses = new ArrayList<>();
        for (Class<?> clazz : getAllClassesFromApiPackage(packageName, scanPath)) {
            if (clazz.isAnnotationPresent(IgnoreCheck.class)) {
                ignoredClasses.add(clazz.getSimpleName());
            }
        }
        if (!ignoredClasses.isEmpty()) {
            System.out.printf("#### Ignored checks %s classes: %n", scanType);
            ignoredClasses.forEach(s -> System.out.println("- " + s));
        }
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

    private String getJavaType(String type) {
        String lowerType = type.toLowerCase();
        if (lowerType.equals("boolean") || lowerType.equals("true")) {
            return "boolean";
        } else if (lowerType.equals("float")) {
            return "double";
        } else if (lowerType.equals("integer")) {
            return "integer|long";
        } else if (lowerType.startsWith("array of")) {
            return "list";
        } else if (lowerType.contains(" or ")) {
            return "complex";
        } else return lowerType;
    }

    @ToString
    @AllArgsConstructor
    static class ApiObject implements ApiEntity {
        String field;
        @Getter
        String type;
        String description;

        static void addElementToEntityMap(Map<String, ApiEntity> apiEntityMap, Element row) {
            String field = row.select("td").get(0).text();
            String type = row.select("td").get(1).text();
            String description = row.select("td").get(2).text();
            apiEntityMap.putIfAbsent(field, new ApiObject(field, type, description));
        }
    }

    @ToString
    @AllArgsConstructor
    static class ApiMethod implements ApiEntity {
        String parameter;
        @Getter
        String type;
        String required;
        String description;

        Boolean getJavaRequiredFlag() {
            String lowerValue = required.toLowerCase();
            if (lowerValue.equals("optional")) {
                return false;
            } else if (lowerValue.equals("yes")) {
                return true;
            } else return null;
        }

        static void addElementToEntityMap(Map<String, ApiEntity> apiEntityMap, Element row) {
            String parameter = row.select("td").get(0).text();
            String type = row.select("td").get(1).text();
            String required = row.select("td").get(2).text();
            String description = row.select("td").get(3).text();
            apiEntityMap.putIfAbsent(parameter, new ApiMethod(parameter, type, required, description));
        }
    }

    public interface ApiEntity {
        String getType();
    }
}

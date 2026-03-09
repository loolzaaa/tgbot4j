# Руководство для контрибьюторов (Contributing Guide)

Спасибо за ваш интерес к разработке этой библиотеки! 
Данное руководство описывает процесс добавления 
новых сущностей (типов данных и методов) **Telegram Bot API**.

Мы стремимся к единообразию кода, поэтому, пожалуйста, 
внимательно следуйте инструкциям ниже.

## Процесс добавления новых сущностей

### 1. Добавление нового типа

Если в документации **Telegram Bot API** появился новый тип (например, `ChatBoost`),
необходимо создать соответствующий класс.

#### Пошаговая инструкция:

1. **Создайте файл класса.** Имя класса должно быть в `PascalCase` 
и строго соответствовать названию типа из документации (например, `ChatBoost`).

2. **Добавьте аннотации Lombok.** Поместите над классом следующие 
три аннотации в указанном порядке:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoost {
    // ...
}
```
*Если у класса нет полей, закомментируйте аннотацию `@AllArgsConstructor`.*

3. **Добавьте JavaDoc для класса.** Скопируйте описание класса 
из официальной документации Telegram.
   - Между блоком импортов и **JavaDoc**'ом класса должна быть **одна пустая строка**.
   - Между **JavaDoc**'ом и аннотациями класса не должно быть пустых строк.

**Пример:**
```java
import com.fasterxml.jackson.annotation.JsonProperty;
// ... другие импорты

/**
 * This object contains information about a chat boost.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoost {
    // ...
}
```

4. **Добавьте поля класса.** Для каждого свойства объекта:

- Объявите поле с модификатором `private`.
- Имя поля должно быть в **camelCase** (например, `boostId`).
- Тип поля должен соответствовать типу свойства. Обратите внимание на особенности:
  - Тип `Float` из документации в коде представляется как `Double`.
  - Тип `File` из документации в коде представляется как `InputFile`.
  - Если указано несколько типов `Integer or String`, то в коде нужно
  оставить `String`.
  - Все массивы в документации должна быть класса `List` для полей класса.
- Добавьте аннотацию `@JsonProperty` со значением имени свойства 
из документации (обычно в **snake_case**).  
Например: `@JsonProperty("boost_id")`.
- Добавьте **JavaDoc** для поля, скопировав его описание из документации.
  - Рекомендуемая ширина **JavaDoc** не должна превышать 70 символов. 

**Требования к форматированию полей:**
- Между **JavaDoc** поля и аннотацией `@JsonProperty` не должно быть пустых строк.
- Между аннотацией `@JsonProperty` и объявлением поля не должно быть пустых строк.
- Между объявлением одного поля и **JavaDoc** следующего поля должна быть **одна пустая строка**.
- Между последним полем и закрывающей скобкой класса пустых строк быть не должно.

**Пример класса целиком:**
```java
package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about a chat boost.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoost {
    /**
     * Unique identifier of the boost
     */
    @JsonProperty("boost_id")
    private String boostId;

    /**
     * Point in time (Unix timestamp) when the chat was boosted
     */
    @JsonProperty("add_date")
    private Integer addDate;

    //... другие поля класса
}
```

### 2. Добавление нового метода

Если в **Telegram Bot API** появился новый метод (например, 
`DeleteStory`), необходимо создать соответствующий класс.

#### Пошаговая инструкция:

1. **Выполните шаги 1-3 из раздела "Добавление нового типа"** 
для создания класса метода. Имя класса должно совпадать 
с названием метода в `PascalCase` (например, `DeleteStory`).
2. **Добавьте поля метода.** Правила для полей такие же, 
как и для обычного типа (пункт 4), но с одним дополнением: 
для обязательных полей добавьте аннотацию `ru.loolzaaa.tgbot4j.core.api.Required`.
    - Аннотация `@Required` должна располагаться **после** **JavaDoc** поля, 
    но **перед** аннотацией `@JsonProperty`.

**Пример поля обязательного параметра:**
```java
/**
 * Unique identifier of the story to edit
 */
@Required
@JsonProperty("story_id")
private Integer storyId;
```

3. **Реализуйте интерфейс метода.** Выберите подходящий интерфейс:
    - Если метод **не требует** загрузки файлов (например, 
    отправка фото по **URL** или **file_id**), реализуйте интерфейс `TelegramMethod`.
    - Если метод **требует** загрузки бинарных данных (например, 
    отправка нового файла с диска), реализуйте интерфейс `TelegramMultipartMethod`.
4. **Реализуйте метод `determineResponseType`.** Этот метод необходим 
для правильной десериализации ответа от **Telegram**.
    - Если метод возвращает **один объект**, используйте конструкцию  
    `return deserializeObjectResponse(mapper, resultNode, ИмяКласса.class);`.
    - Если метод возвращает **коллекцию объектов** (например, массив), используйте конструкцию  
    `return deserializeCollectionResponse(mapper, resultNode, ИмяКласса.class);`.
    - Тип возвращаемого значения самого метода в классе должен соответствовать ожидаемому:  
    `ИмяКласса` для одного объекта или `List<ИмяКласса>` для коллекции.

Пример для метода, возвращающего `List<BotCommand>`:
```java
@Override
public List<BotCommand> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
    return deserializeCollectionResponse(mapper, resultNode, BotCommand.class);
}
```

### 3. Обновление существующих интерфейсных типов

Некоторые типы в **Telegram API** являются полиморфными 
(например, `ChatMember` может быть `ChatMemberOwner`, `ChatMemberLeft` и др.).

1. При добавлении нового варианта такого типа (например, `ChatMemberRestricted`), 
необходимо, чтобы новый класс реализовал общий интерфейс (в данном случае `ChatMember`).
2. В общем интерфейсе (`ChatMember`) убедитесь, что аннотация `@JsonTypeInfo`
и `@JsonSubTypes` обновлены для включения нового класса.
3. Обновите **JavaDoc** интерфейса, если это необходимо (например, 
добавив ссылку на новый класс с помощью тега `@see`).

Пример для интерфейсного типа `ChatMember`:
```java
/**
 * This object contains information about one member of a chat.
 * Currently, the following 6 types of chat members are supported:
 *
 * <ul>
 *     <li>{@link ChatMemberOwner}</li>
 *     <li>{@link ChatMemberAdministrator}</li>
 *     <li>{@link ChatMemberMember}</li>
 *     <li>{@link ChatMemberRestricted}</li>
 *     <li>{@link ChatMemberLeft}</li>
 *     <li>{@link ChatMemberBanned}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "status")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatMemberOwner.class, name = "creator"),
        @JsonSubTypes.Type(value = ChatMemberAdministrator.class, name = "administrator"),
        @JsonSubTypes.Type(value = ChatMemberMember.class, name = "member"),
        @JsonSubTypes.Type(value = ChatMemberRestricted.class, name = "restricted"),
        @JsonSubTypes.Type(value = ChatMemberLeft.class, name = "left"),
        @JsonSubTypes.Type(value = ChatMemberBanned.class, name = "kicked"),
})
public interface ChatMember {
}
```

## Проверка результатов

### Проверка спецификации

После завершения добавления/изменения новых сущностей необходимо запустить
модуль для проверки спецификации API: `ru.loolzaaa.tgbot4j.core.check.ApiSpecificationChecker`.

Данный модуль проверить корректность всех сущностей по нескольким критериям,
после чего укажет на выявленные ошибки.

### Обновление архива

Часто при обновлении API команда Telegram не указывает всех мелких изменений
в описании сущностей, поэтому необходимо слить дамп документации
и провести сравнение с имеющейся версией:

1. Запустить модуль по скачиванию дампа документации 
`ru.loolzaaa.tgbot4j.core.check.BotApiArchiveUpdater`
2. Провести построчное сравнение между старым дампом `archive/bot_api`
и новым дампом `archive/bot_api_<дата загрузки дампа>`
3. Обновить текущие сущности изменениями согласно сравнению дампов
4. Подменить старый дамп новым (имя старого дампа всегда `bot_api`)

*Все датированные дампы не сохраняются в репозитории, 
т.к. включены в `.gitignore`*

---

Еще раз спасибо за ваш вклад! Если у вас возникнут вопросы, 
вы всегда можете открыть **issue** для обсуждения.
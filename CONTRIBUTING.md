# Contributing Guide

Thank you for your interest in contributing to this library!
This guide describes the process for adding new entities 
(data types and methods) to the **Telegram Bot API**.

We strive for code consistency, so please follow the instructions 
below carefully.

## Process for Adding New Entities

### 1. Adding a New Type

If a new type appears in the **Telegram Bot API** documentation 
(e.g., `ChatBoost`), you need to create the corresponding class.

#### Step-by-Step Instructions:

1. **Create the class file.** The class name must be in `PascalCase` 
and strictly match the type name from the documentation (e.g., `ChatBoost`).

2. **Add Lombok annotations.** Place the following three annotations 
above the class in the specified order:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBoost {
    // ...
}
```
*If the class has no fields, comment out the `@AllArgsConstructor` annotation.*

3. **Add JavaDoc for the class.** Copy the class description 
from the official Telegram documentation.
   - There must be **one empty line** between the import block and the class **JavaDoc**.
   - There must be **no empty lines** between the **JavaDoc** and the class annotations.

**Пример:**
```java
import com.fasterxml.jackson.annotation.JsonProperty;
// ... other imports

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

4. **Add the class fields.** For each object property:

- Declare the field with the `private` modifier.
- The field name must be in **camelCase** (e.g., `boostId`).
- The field type must correspond to the property type. Pay attention to the specifics:
  - The `Float` type from the documentation is represented as `Double` in the code.
  - The `File` type from the documentation is represented as `InputFile`.
  - If multiple types are specified, like `Integer or String`, use `String` in the code.
  - All arrays in the documentation should be represented as a `List` for class fields.
- Add the `@JsonProperty` annotation with the property name 
from the documentation (usually in **snake_case**).
For example: `@JsonProperty("boost_id")`.
- Add **JavaDoc** for the field, copying its description from the documentation.
  - The recommended width for **JavaDoc** should not exceed 70 characters.

**Field Formatting Requirements:**
- There must be **no empty lines** between the field's **JavaDoc** and the `@JsonProperty` annotation.
- There must be **no empty lines** between the `@JsonProperty` annotation and the field declaration.
- There must be **one empty line** between the declaration of one field and the **JavaDoc** of the next field.
- There must be **no empty lines** between the last field and the closing brace of the class.

**Complete Class Example:**
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

    //... other class fields
}
```

### 2. Adding a New Method

If a new method appears in the **Telegram Bot API** (e.g., `DeleteStory`), 
you need to create the corresponding class.

#### Step-by-Step Instructions:

1. **Follow steps 1-3 from the "Adding a New Type" section** 
to create the method class. The class name must match the method name 
in `PascalCase` (e.g., `DeleteStory`).
2. **Add the method fields.** The rules for fields are the same as 
for a regular type (step 4), but with one addition: for required fields, 
add the `ru.loolzaaa.tgbot4j.core.api.Required` annotation.
    - The `@Required` annotation must be placed **after** the field's **JavaDoc**, 
    but **before** the `@JsonProperty` annotation.

**Example of a Required Parameter Field:**
```java
/**
 * Unique identifier of the story to edit
 */
@Required
@JsonProperty("story_id")
private Integer storyId;
```

3. **Implement the method interface.** Choose the appropriate interface:
    - If the method **does not require** file uploads (e.g., sending a photo via **URL** or **file_id**), 
    implement the `TelegramMethod` interface.
    - If the method **requires** uploading binary data (e.g., sending a new file from disk), 
    implement the `TelegramMultipartMethod` interface.
4. **Implement the `determineResponseType` method.** This method is necessary 
for correctly deserializing the response from **Telegram**.
    - If the method returns a **single object**, use the construct:
      `return deserializeObjectResponse(mapper, resultNode, ClassName.class);`
    - If the method returns a **collection of objects** (e.g., an array), use the construct:
      `return deserializeCollectionResponse(mapper, resultNode, ClassName.class);`
    - The return type of the method itself in the class must match the expectation:
    `ClassName` for a single object or `List<ClassName>` for a collection.

Example for a method returning `List<BotCommand>`:
```java
@Override
public List<BotCommand> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
    return deserializeCollectionResponse(mapper, resultNode, BotCommand.class);
}
```

### 3. Updating Existing Interface Types

Some types in the **Telegram API** are polymorphic (e.g., `ChatMember` 
can be `ChatMemberOwner`, `ChatMemberLeft`, etc.).

1. When adding a new variant of such a type (e.g., `ChatMemberRestricted`), 
the new class must implement the common interface (in this case, `ChatMember`).
2. In the common interface (`ChatMember`), ensure that the `@JsonTypeInfo` 
and `@JsonSubTypes` annotations are updated to include the new class.
3. Update the interface's **JavaDoc** if necessary (e.g., by adding a link 
to the new class using the `@see` tag).

Example for the `ChatMember` interface type:
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

## Verifying the Results

### Specification Check

After finishing adding/changing new entities, 
you need to run the module to check the API specification: 
`ru.loolzaaa.tgbot4j.core.check.ApiSpecificationChecker`.

This module will check the correctness of all entities against 
several criteria and then point out any identified errors.

### Archive Update

ЧOften, when the API is updated, the Telegram team does not list 
all minor changes in the entity descriptions. Therefore, it is necessary 
to download a dump of the documentation and compare it with the existing version:

1. Run the module to download the documentation dump:
`ru.loolzaaa.tgbot4j.core.check.BotApiArchiveUpdater`
2. Perform a line-by-line comparison between the old dump `archive/bot_api` 
and the new dump `archive/bot_api_<dump download date>`.
3. Update the current entities with the changes identified in the dump comparison.
4. Replace the old dump with the new one (the name of the old dump is always `bot_api`).

*All dated dumps are not saved in the repository, 
as they are included in `.gitignore`.*

---

Thank you again for your contribution! If you have any questions, 
you can always open an **issue** for discussion.
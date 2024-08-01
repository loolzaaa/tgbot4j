package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the way a background is filled
 * based on the selected colors. Currently, it can be one of
 * <ul>
 *     <li>{@link BackgroundFillSolid}</li>
 *     <li>{@link BackgroundFillGradient}</li>
 *     <li>{@link BackgroundFillFreeformGradient}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BackgroundFillSolid.class, name = "solid"),
        @JsonSubTypes.Type(value = BackgroundFillGradient.class, name = "gradient"),
        @JsonSubTypes.Type(value = BackgroundFillFreeformGradient.class, name = "freeform_gradient"),
})
public interface BackgroundFill {
}

package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the state of a revenue withdrawal operation.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link RevenueWithdrawalStatePending}</li>
 *     <li>{@link RevenueWithdrawalStateSucceeded}</li>
 *     <li>{@link RevenueWithdrawalStateFailed}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RevenueWithdrawalStatePending.class, name = "pending"),
        @JsonSubTypes.Type(value = RevenueWithdrawalStateSucceeded.class, name = "succeeded"),
        @JsonSubTypes.Type(value = RevenueWithdrawalStateFailed.class, name = "failed"),
})
public interface RevenueWithdrawalState {
}

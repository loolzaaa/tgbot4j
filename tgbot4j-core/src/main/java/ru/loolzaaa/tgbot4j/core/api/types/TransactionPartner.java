package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object describes the source of a transaction,
 * or its recipient for outgoing transactions.
 * Currently, it can be one of
 * <ul>
 *     <li>{@link TransactionPartnerUser}</li>
 *     <li>{@link TransactionPartnerFragment}</li>
 *     <li>{@link TransactionPartnerTelegramAds}</li>
 *     <li>{@link TransactionPartnerTelegramApi}</li>
 *     <li>{@link TransactionPartnerOther}</li>
 *     <li>{@link TransactionPartnerAffiliateProgram}</li>
 *     <li>{@link TransactionPartnerChat}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TransactionPartnerUser.class, name = "user"),
        @JsonSubTypes.Type(value = TransactionPartnerFragment.class, name = "fragment"),
        @JsonSubTypes.Type(value = TransactionPartnerTelegramAds.class, name = "telegram_ads"),
        @JsonSubTypes.Type(value = TransactionPartnerTelegramApi.class, name = "telegram_api"),
        @JsonSubTypes.Type(value = TransactionPartnerOther.class, name = "other"),
        @JsonSubTypes.Type(value = TransactionPartnerAffiliateProgram.class, name = "affiliate_program"),
        @JsonSubTypes.Type(value = TransactionPartnerChat.class, name = "chat"),
})
public interface TransactionPartner {
}

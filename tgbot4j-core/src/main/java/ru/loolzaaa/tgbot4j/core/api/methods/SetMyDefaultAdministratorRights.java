package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatAdministratorRights;

/**
 * Use this method to change the default administrator rights
 * requested by the bot when it's added as an administrator to groups or channels.
 * These rights will be suggested to users,
 * but they are free to modify the list before adding the bot.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMyDefaultAdministratorRights implements TelegramMethod<Boolean> {
    /**
     * A JSON-serialized object describing new default administrator rights.
     * If not specified, the default administrator rights will be cleared.
     */
    @JsonProperty("rights")
    private ChatAdministratorRights rights;

    /**
     * Pass True to change the default administrator rights of the bot in channels.
     * Otherwise, the default administrator rights of the bot
     * for groups and supergroups will be changed.
     */
    @JsonProperty("for_channels")
    private Boolean forChannels;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

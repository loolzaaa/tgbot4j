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
 * Use this method to get the current default administrator rights of the bot.
 * Returns {@link ChatAdministratorRights} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyDefaultAdministratorRights implements TelegramMethod<ChatAdministratorRights> {
    /**
     * Pass True to change the default administrator rights of the bot in channels.
     * Otherwise, the default administrator rights of the bot
     * for groups and supergroups will be returned.
     */
    @JsonProperty("for_channels")
    private Boolean forChannels;

    @Override
    public ChatAdministratorRights determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, ChatAdministratorRights.class);
    }
}

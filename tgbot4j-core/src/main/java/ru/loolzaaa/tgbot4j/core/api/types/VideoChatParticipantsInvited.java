package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a service message
 * about new members invited to a video chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoChatParticipantsInvited {
    /**
     * New members that were invited to the video chat
     */
    @JsonProperty("users")
    private List<User> users;
}

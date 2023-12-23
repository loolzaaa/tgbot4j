package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an invite link for a chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatInviteLink {
    /**
     * The invite link. If the link was created by another chat administrator,
     * then the second part of the link will be replaced with “…”.
     */
    @JsonProperty("invite_link")
    private String inviteLink;

    /**
     * Creator of the link
     */
    @JsonProperty("creator")
    private User creator;

    /**
     * True, if users joining the chat via the link need to be approved by chat administrators
     */
    @JsonProperty("creates_join_request")
    private Boolean createsJoinRequest;

    /**
     * True, if the link is primary
     */
    @JsonProperty("is_primary")
    private Boolean isPrimary;

    /**
     * True, if the link is revoked
     */
    @JsonProperty("is_revoked")
    private Boolean isRevoked;

    /**
     * Optional. Invite link name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. Point in time (Unix timestamp)
     * when the link will expire or has been expired
     */
    @JsonProperty("expire_date")
    private Integer expireDate;

    /**
     * Optional. The maximum number of users that can be members
     * of the chat simultaneously after joining the chat
     * via this invite link; 1-99999
     */
    @JsonProperty("member_limit")
    private Integer memberLimit;

    /**
     * Optional. Number of pending join requests created using this link
     */
    @JsonProperty("pending_join_request_count")
    private Integer pendingJoinRequestCount;
}

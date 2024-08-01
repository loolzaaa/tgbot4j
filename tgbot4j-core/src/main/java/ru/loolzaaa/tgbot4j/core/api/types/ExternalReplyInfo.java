package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about a message
 * that is being replied to, which may come from
 * another chat or forum topic.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalReplyInfo {
    /**
     * Origin of the message replied to by the given message
     */
    @JsonProperty("origin")
    private MessageOrigin origin;

    /**
     * Optional. Chat the original message belongs to.
     * Available only if the chat is a supergroup or a channel.
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Optional. Unique message identifier inside the original chat.
     * Available only if the original chat is a supergroup or a channel.
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Optional. Options used for link preview generation
     * for the original message, if it is a text message
     */
    @JsonProperty("link_preview_options")
    private LinkPreviewOptions linkPreviewOptions;

    /**
     * Optional. Message is an animation,
     * information about the animation
     */
    @JsonProperty("animation")
    private Animation animation;

    /**
     * Optional. Message is an audio file,
     * information about the file
     */
    @JsonProperty("audio")
    private Audio audio;

    /**
     * Optional. Message is a general file,
     * information about the file
     */
    @JsonProperty("document")
    private Document document;

    /**
     * Optional. Message contains paid media;
     * information about the paid media
     */
    @JsonProperty("paid_media")
    private PaidMediaInfo paidMedia;

    /**
     * Optional. Message is a photo,
     * available sizes of the photo
     */
    @JsonProperty("photo")
    private List<PhotoSize> photo;

    /**
     * Optional. Message is a sticker,
     * information about the sticker
     */
    @JsonProperty("sticker")
    private Sticker sticker;

    /**
     * Optional. Message is a forwarded story
     */
    @JsonProperty("story")
    private Story story;

    /**
     * Optional. Message is a video,
     * information about the video
     */
    @JsonProperty("video")
    private Video video;

    /**
     * Optional. Message is a <a href="https://telegram.org/blog/video-messages-and-telescope">video note</a>,
     * information about the video message
     */
    @JsonProperty("video_note")
    private VideoNote videoNote;

    /**
     * Optional. Message is a voice message,
     * information about the file
     */
    @JsonProperty("voice")
    private Voice voice;

    /**
     * Optional. True, if the message media
     * is covered by a spoiler animation
     */
    @JsonProperty("has_media_spoiler")
    private Boolean hasMediaSpoiler;

    /**
     * Optional. Message is a shared contact,
     * information about the contact
     */
    @JsonProperty("contact")
    private Contact contact;

    /**
     * Optional. Message is a dice with random value
     */
    @JsonProperty("dice")
    private Dice dice;

    /**
     * Optional. Message is a game,
     * information about the game.
     * <a href="https://core.telegram.org/bots/api#games">More about games »</a>
     */
    @JsonProperty("game")
    private Game game;

    /**
     * Optional. Message is a scheduled giveaway,
     * information about the giveaway
     */
    @JsonProperty("giveaway")
    private Giveaway giveaway;

    /**
     * Optional. A giveaway with public winners was completed
     */
    @JsonProperty("giveaway_winners")
    private GiveawayWinners giveawayWinners;

    /**
     * Optional. Message is an invoice for a payment,
     * information about the invoice.
     * <a href="https://core.telegram.org/bots/api#payments">More about payments »</a>
     */
    @JsonProperty("invoice")
    private Invoice invoice;

    /**
     * Optional. Message is a shared location,
     * information about the location
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Optional. Message is a native poll,
     * information about the poll
     */
    @JsonProperty("poll")
    private Poll poll;

    /**
     * Optional. Message is a venue,
     * information about the venue
     */
    @JsonProperty("venue")
    private Venue venue;
}

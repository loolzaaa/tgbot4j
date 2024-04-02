package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.File;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to get basic information
 * about a file and prepare it for downloading.
 * For the moment, bots can download files of up to 20MB in size.
 * On success, a {@link File} object is returned.
 * The file can then be downloaded via
 * the link {@code https://api.telegram.org/file/bot<token>/<file_path>},
 * where {@code <file_path>} is taken from the response.
 * It is guaranteed that the link will be valid for at least 1 hour.
 * When the link expires, a new one can be requested by calling {@link GetFile} again.
 *
 * @implNote Note: This function may not preserve the original file name and MIME type.
 * You should save the file's MIME type and
 * name (if available) when the File object is received.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFile implements TelegramMethod<File> {
    /**
     * File identifier to get information about
     */
    @Required
    @JsonProperty("file_id")
    private String fileId;

    @Override
    public File determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, File.class);
    }

    @Override
    public void validate() {
        if (fileId == null) {
            throw new ApiValidationException("File ID parameter can't be null", this);
        }
    }
}

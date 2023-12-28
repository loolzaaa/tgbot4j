package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Validated;

import java.io.File;
import java.io.InputStream;

/**
 * This object represents the contents of a file to be uploaded.
 * Must be posted using multipart/form-data in the usual way
 * that files are uploaded via the browser.
 * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
 */

@Data
@NoArgsConstructor
public class InputFile implements Validated {

    private static final String ATTACH_PREFIX = "attach://";

    private String attachName;

    @JsonIgnore
    private String inputName;
    @JsonIgnore
    private File file;
    @JsonIgnore
    private InputStream inputStream;

    public InputFile(String fileId) {
        this.attachName = fileId;
    }

    public InputFile(String attachName, File file) {
        this.attachName = ATTACH_PREFIX + attachName;
        this.inputName = attachName;
        this.file = file;
    }

    public InputFile(String attachName, InputStream inputStream) {
        this.attachName = ATTACH_PREFIX + attachName;
        this.inputName = attachName;
        this.inputStream = inputStream;
    }

    @Override
    public void validate() {
        if (attachName == null || attachName.isEmpty()) {
            throw new RuntimeException("Attach name must not be empty");
        }
        if (attachName.startsWith(ATTACH_PREFIX)) {
            if (inputName == null || inputName.isEmpty()) {
                throw new RuntimeException("Input name must not be empty");
            }
            if (file == null && inputStream == null) {
                throw new RuntimeException("File or input stream must not be empty");
            }
        }
    }
}

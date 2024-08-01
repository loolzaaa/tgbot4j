package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Contains a list of Telegram Star transactions.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarTransactions {
    /**
     * The list of transactions
     */
    @JsonProperty("transactions")
    private List<StarTransaction> transactions;
}

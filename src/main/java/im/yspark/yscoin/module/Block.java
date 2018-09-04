package im.yspark.yscoin.module;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class Block {
    private int index;
    private long timestamp;
    private List<Transaction> transactions;
    private long proof;
    private String previousHash;

    public Block(int index, List<Transaction> transactions, long proof, String previousHash) {
        this.index = index;
        this.timestamp = LocalDateTime.now().atZone(ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0))).toEpochSecond();
        this.transactions = transactions;
        this.proof = proof;
        this.previousHash = previousHash;
    }
}

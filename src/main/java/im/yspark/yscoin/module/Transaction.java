package im.yspark.yscoin.module;

import lombok.Data;

@Data
public class Transaction {
    private String sender;
    private String recipient;
    private long amount;

    public Transaction(String sender, String recipient, long amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Transaction(long amount) {
        this.recipient = BlockChain.getInstance().getNodeIdentifier();
        this.amount = amount;
        this.sender = "";
    }
}

package im.yspark.yscoin.module;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private List<Block> chain;
    private List<Transaction> currentTransactions;

    public BlockChain() {
        chain = new ArrayList<>();
        currentTransactions = new ArrayList<>();
    }

    public Block newBlock(int proof) {
        Block block = new Block(chain.size(), currentTransactions, proof, hash(chain.get(chain.size() - 1)));
        currentTransactions = new ArrayList<>();
        chain.add(block);
        return block;
    }

    public Block newBlock(long proof, String previousHash) {
        Block block = new Block(chain.size(), currentTransactions, proof, previousHash);
        currentTransactions = new ArrayList<>();
        chain.add(block);
        return block;
    }

    public int newTransaction(String sender, String recipient, long amount) {
        currentTransactions.add(new Transaction(sender, recipient, amount));
        return this.chain.size();
    }

    public static String hash(Block block) {
        String json = JSON.toJSONString(block);
        return hash(json);
    }

    public static String hash(String text) {
        return Hashing.sha256().hashString(text, StandardCharsets.UTF_8).toString();
    }

    public void lastBlock() {

    }

    public long proofWork(long lastProof) {
        long proof = 0L;
        while(!proof(lastProof, proof)) {
            proof++;
        }
        return proof;
    }

    public boolean proof(long lastProof, long proof) {
        String proofStrr = lastProof + "" + proof;
        String hashed = hash(proofStrr);
        return hashed.startsWith("0000");
    }
}

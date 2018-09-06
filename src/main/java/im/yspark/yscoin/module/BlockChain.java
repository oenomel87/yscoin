package im.yspark.yscoin.module;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class BlockChain {

    private static BlockChain blockChain;
    private List<Block> chain;
    private List<Transaction> currentTransactions;
    private String nodeIdentifier;

    public static BlockChain getInstance() {
        if(blockChain == null) {
            blockChain = new BlockChain();
        }
        return blockChain;
    }

    private BlockChain() {
        chain = new ArrayList<>();
        currentTransactions = new ArrayList<>();
        nodeIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
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

    public int newTransaction(Transaction transaction) {
        currentTransactions.add(transaction);
        return chain.size();
    }

    public int newTransaction(String sender, String recipient, long amount) {
        return newTransaction(new Transaction(sender, recipient, amount));
    }

    public static String hash(Block block) {
        String json = JSON.toJSONString(block);
        return hash(json);
    }

    public static String hash(String text) {
        return Hashing.sha256().hashString(text, StandardCharsets.UTF_8).toString();
    }

    public Block lastBlock() {
        return chain.get(chain.size() - 1);
    }

    public long proofWork(long lastProof) {
        long proof = 0L;
        while(!validProof(lastProof, proof)) {
            proof++;
        }
        return proof;
    }

    public boolean validProof(long lastProof, long proof) {
        String proofStr = lastProof + "" + proof;
        String hashed = hash(proofStr);
        return hashed.startsWith("0000");
    }

    public Map<String,Object> getChain() {
        Map<String,Object> data = new HashMap<>();
        data.put("chain", chain);
        data.put("length", chain.size());
        return data;
    }

    public String getNodeIdentifier() {
        return nodeIdentifier;
    }
}

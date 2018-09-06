package im.yspark.yscoin.service;

import com.alibaba.fastjson.JSON;
import im.yspark.yscoin.module.Block;
import im.yspark.yscoin.module.BlockChain;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MineService {
    public String mining() {
        Block lastBlock = BlockChain.getInstance().lastBlock();
        long lastProof = lastBlock.getProof();
        long proof = BlockChain.getInstance().proofWork(lastProof);
        BlockChain.getInstance().newTransaction("0", BlockChain.getInstance().getNodeIdentifier(), 1);
        String previousHash = BlockChain.hash(lastBlock);
        Block newBlock = BlockChain.getInstance().newBlock(proof, previousHash);
        Map<String,Object> response = generateResponse(newBlock);
        return JSON.toJSONString(response);
    }

    private Map<String,Object> generateResponse(Block newBlock) {
        Map<String,Object> response = new HashMap<>();
        response.put("message", "New Block forged");
        response.put("index", newBlock.getIndex());
        response.put("transaction", newBlock.getTransactions());
        response.put("proof", newBlock.getProof());
        response.put("previousHash", newBlock.getPreviousHash());
        return response;
    }
}

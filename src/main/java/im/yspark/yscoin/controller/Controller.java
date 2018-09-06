package im.yspark.yscoin.controller;

import com.alibaba.fastjson.JSON;
import im.yspark.yscoin.module.BlockChain;
import im.yspark.yscoin.module.Transaction;
import im.yspark.yscoin.service.MineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private MineService mineService;

    @PostMapping(value = "transaction/new")
    public String newTransactionController(@RequestBody Transaction transaction) {
        int index = BlockChain.getInstance().newTransaction(transaction);
        return "{\"result\":\"Transaction will be added to Block " + index + "\"}";
    }

    @GetMapping(value = "mine")
    public String mine() {
        return mineService.mining();
    }

    @GetMapping(value = "chain")
    public String fullChain() {
        return JSON.toJSONString(BlockChain.getInstance().getChain());
    }
}

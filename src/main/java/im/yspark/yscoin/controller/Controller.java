package im.yspark.yscoin.controller;

import com.alibaba.fastjson.JSON;
import im.yspark.yscoin.module.BlockChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @PostMapping(value = "transaction/new")
    public String newTransactionController() {
        return "net transaction added";
    }

    @GetMapping(value = "mine")
    public String mine() {
        return "mining";
    }

    @GetMapping(value = "chain")
    public String fullChain() {
        return JSON.toJSONString(BlockChain.getInstance().getChain());
    }
}

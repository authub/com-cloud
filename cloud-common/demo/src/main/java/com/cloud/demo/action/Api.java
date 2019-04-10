package com.cloud.demo.action;

import com.cloud.demo.cinfig.ResBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @Title Api.java
 * @Package com.cloud.demo.action
 * @Author <a href="mailto:tuanyu@sinotn.com">au .T</a>
 * @Date 2019/3/31 19:12
 */
@RestController
@RequestMapping("/api")
public class Api {
    @Autowired
    HttpServletRequest request;
    @Value("${server.port}")
    String port;

    @GetMapping("/port")
    public Object port() {
        Map<String, String> map = new HashMap<>();
        map.put("port", port);
        return map;
    }

    @GetMapping("/head")
    public Object head() {
        String head = request.getHeader("demoHeader");
        Map<String, String> map = new HashMap<>();
        map.put("head", head);
return map;
//        return new ResBody<>(map);
//        return "head info:" + head;

    }

    @GetMapping("/index")
    public Object index() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put("index" + i, "map" + 1);
        }
        map.put("index", "map");
        return map;
    }

    @GetMapping("/error")
    public Object error() {

        if (true) {
            throw new RuntimeException("抛出RuntimeException异常");
        }
        return null;
    }
}

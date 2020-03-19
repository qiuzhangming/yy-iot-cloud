package cn.zzdz.device.controller;

import com.alibaba.nacos.common.util.UuidUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello:" + UuidUtils.generateUuid();
    }

    @GetMapping("/say")
    public String say() {
        return "say:" + UuidUtils.generateUuid();
    }
}

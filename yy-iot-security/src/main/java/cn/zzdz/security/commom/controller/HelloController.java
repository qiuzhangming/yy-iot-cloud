package cn.zzdz.security.commom.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequiresPermissions("perms:hello")
    @RequiresRoles("role:hello")
    @GetMapping("")
    public String sayHello() {
        return "hello 你好呀。" + new Date().toString();
    }
}

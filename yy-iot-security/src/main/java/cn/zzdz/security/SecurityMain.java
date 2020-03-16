package cn.zzdz.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(value = "cn.zzdz.common.entity.security")
@EnableTransactionManagement
@SpringBootApplication
public class SecurityMain {
    public static void main(String[] args) {
        SpringApplication.run(SecurityMain.class, args);
    }
}

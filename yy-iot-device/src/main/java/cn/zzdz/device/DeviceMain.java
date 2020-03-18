package cn.zzdz.device;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.zzdz.api")
@MapperScan(value = "cn.zzdz.device.dao")
@EnableTransactionManagement
@SpringBootApplication
public class DeviceMain {
    public static void main(String[] args) {
        SpringApplication.run(DeviceMain.class, args);
    }
}

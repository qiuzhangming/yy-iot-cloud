package cn.zzdz.device.config;

import cn.zzdz.common.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyIdWorker {
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}

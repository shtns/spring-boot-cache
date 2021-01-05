package com.sh.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 缓存主启动
 *
 *
 * @author 盛浩
 * @date 2021/1/2 14:47
 */
@EnableCaching
@SpringBootApplication
@MapperScan(value = "com.sh.cache.employee.mapper")
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}

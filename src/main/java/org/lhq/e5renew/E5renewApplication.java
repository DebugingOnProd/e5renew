package org.lhq.e5renew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lhq.e5renew.dao")
public class E5renewApplication {

    public static void main(String[] args) {
        SpringApplication.run(E5renewApplication.class, args);
    }

}

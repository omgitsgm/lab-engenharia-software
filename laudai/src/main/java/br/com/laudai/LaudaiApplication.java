package br.com.laudai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LaudaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaudaiApplication.class, args);
    }

}

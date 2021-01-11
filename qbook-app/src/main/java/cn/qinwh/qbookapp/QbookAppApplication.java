package cn.qinwh.qbookapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.qinwh.*"})
@MapperScan(basePackages = {"cn.qinwh.qbookapp.mapper","cn.qinwh.qbooksystem.mapper"})
public class QbookAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(QbookAppApplication.class, args);
    }

}

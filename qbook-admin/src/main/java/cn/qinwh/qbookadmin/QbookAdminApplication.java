package cn.qinwh.qbookadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.qinwh.*"})
@MapperScan({"cn.qinwh.*"})
public class QbookAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(QbookAdminApplication.class, args);
    }

}

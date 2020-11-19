package cn.qinwh.qbookadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.qinwh.*"})
public class QbookAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(QbookAdminApplication.class, args);
    }

}

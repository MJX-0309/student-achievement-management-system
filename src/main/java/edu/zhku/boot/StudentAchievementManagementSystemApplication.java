package edu.zhku.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan("edu.zhku.boot.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StudentAchievementManagementSystemApplication {
    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }
    public static void main(String[] args) {
        SpringApplication.run(StudentAchievementManagementSystemApplication.class, args);
    }

}

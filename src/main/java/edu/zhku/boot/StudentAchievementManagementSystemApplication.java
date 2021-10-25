package edu.zhku.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("edu.zhku.boot.mapper")
@SpringBootApplication
public class StudentAchievementManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentAchievementManagementSystemApplication.class, args);
    }

}

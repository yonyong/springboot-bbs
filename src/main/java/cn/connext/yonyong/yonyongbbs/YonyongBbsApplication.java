package cn.connext.yonyong.yonyongbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.connext.yonyong.yonyongbbs.dao")
public class YonyongBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YonyongBbsApplication.class, args);
    }

}


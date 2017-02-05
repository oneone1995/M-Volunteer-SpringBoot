package com.github.oneone1995.mvolunteer;

import com.github.oneone1995.mvolunteer.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.github.oneone1995.mvolunteer.mapper", markerInterface = MyMapper.class)
public class MVolunteerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVolunteerApplication.class, args);
	}
}

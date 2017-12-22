package com.github.oneone1995.mvolunteer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MVolunteerApplicationTests {

	@Test
	public void contextLoads() {
	}

	//往redis中预先放1000个随机码
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void listPushResitTest() {
		Set<String> codes = new HashSet<>(1024);
		while (codes.size() != 1000) {
			String s = String.valueOf(new Random().nextInt(999999));
			if (s.length() == 6) {
				codes.add(s);
			}
		}
		List<String> result = new ArrayList<>(codes);
		result.forEach(s -> redisTemplate.opsForList().leftPush("activity-code", s));
	}
}

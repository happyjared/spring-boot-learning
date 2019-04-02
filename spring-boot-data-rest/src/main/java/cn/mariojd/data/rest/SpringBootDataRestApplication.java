package cn.mariojd.data.rest;

import cn.mariojd.data.rest.entity.User;
import cn.mariojd.data.rest.enums.Gender;
import cn.mariojd.data.rest.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SpringBootDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataRestApplication.class, args);
    }

    @Resource
    private UserRepository userRepository;

    /**
     * 初始化数据
     */
    @PostConstruct
    public void init() {
        EnumSet<Gender> genders = EnumSet.allOf(Gender.class);
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            User user = User.builder().name("test" + i)
                    .gender(genders.stream().findAny().get()).build();
            users.add(user);
        }
        userRepository.saveAll(users);
    }

}

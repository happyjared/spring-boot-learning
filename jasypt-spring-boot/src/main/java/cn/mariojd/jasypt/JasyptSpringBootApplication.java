package cn.mariojd.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
@EnableEncryptableProperties
public class JasyptSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(JasyptSpringBootApplication.class, args);
        JasyptSpringBootApplication application = context.getBean(JasyptSpringBootApplication.class);
        // 这里可以将明文(用户名、密码)转换成相应密文
        application.jasypt("root");
        application.jasypt("123456");

        // 不过程序最后还是通过明文信息进行数据库连接
        HikariDataSource hikariDataSource = (HikariDataSource) context.getBean(DataSource.class);
        log.info("DB username: {} , password: {}", hikariDataSource.getUsername(), hikariDataSource.getUsername());
    }

    @Resource
    private StringEncryptor stringEncryptor;

    public void jasypt(String text) {
        // 即使是相同明文，但这里每次生成的都是不同的密文
        String encryptedText = stringEncryptor.encrypt(text.trim());
        String decryptedText = stringEncryptor.decrypt(encryptedText);
        log.info("ORIGINAL: {} ; ENCRYPTED: {} ; DECRYPTED: {}", text, encryptedText, decryptedText);
    }

}

package cn.mariojd.jsr303.valid.vo;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author Jared
 * @date 2019/1/15 10:22
 */
@Data
public class ValidVO {

    @Null(message = "test为null")
    private Integer test;

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "name不能为空")
    private String name;

    @Size(min = 1, max = 10, message = "stringList size should between 1-10")
    @NotEmpty(message = "stringList must not empty")
    private List<String> stringList;

    @AssertTrue(message = "isTrue should be True")
    private Boolean isTrue;

    @AssertFalse(message = "isFalse should be False")
    private Boolean isFalse;

    @Length(min = 8, max = 30, message = "Email length should between 8-30")
    @Email(message = "邮箱格式错误")
    private String email;

    @Future(message = "futureDate是未来日期")
    private Date futureDate;

    @Past(message = "pastDate是过去日期")
    private Date pastDate;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "手机号码有误")
    private String phone;

    @Min(value = 0, message = "最小Score值为0")
    @Max(value = 100, message = "最大Score值为0")
    @Range(min = 0, max = 100, message = "最小Score值为0 && 最大Score值为0")
    private Integer score;

    @CreditCardNumber(message = "信用卡号码有误")
    private String creditCardNumber;

    @URL(protocol = "http", host = "127.0.0.1", port = 8080, message = "URL数据异常")
    private String url;

    private String result;

}

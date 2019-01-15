package cn.mariojd.jsr303.valid.vo;

import lombok.Data;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jared
 * @date 2019/1/15 10:22
 */
@Data
public class ValidVO {

    // 空和非空检查: @Null、@NotNull、@NotBlank、@NotEmpty

    @Null(message = "test is null")
    private Integer test;

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotEmpty(message = "stringList must not empty")
    private List<String> stringList;

    // Boolean值检查: @AssertTrue、@AssertFalse

    @AssertTrue(message = "isTrue should be True")
    private Boolean isTrue;

    @AssertFalse(message = "isFalse should be False")
    private Boolean isFalse;

    // 长度检查: @Size、@Length

    @Size(min = 1, max = 2, message = "integerList size should between 1-2")
    private List<Integer> integerList;

    @Length(min = 8, max = 30, message = "Address length should between 8-30")
    private String address;

    // 日期检查: @Future、@FutureOrPresent、@Past、@PastOrPresent

    @Future(message = "futureDate is a future date")
    private Date futureDate;

    @Future(message = "FutureOrPresent is a future or Present date")
    private Date futureOrPresentDate;

    @Past(message = "pastDate is a past date")
    private Date pastDate;

    @Past(message = "pastOrPresentDate is a past or Present date")
    private Date pastOrPresentDate;

    // 其它检查: @Email、@CreditCardNumber、@URL、@Pattern、@ScriptAssert、@UniqueElements

    @Email(message = "Email format error")
    private String email;

    @CreditCardNumber(message = "credit card number error")
    private String creditCardNumber;

    @URL(protocol = "http", host = "127.0.0.1", port = 8080, message = "URL error")
    private String url;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "phone format error")
    private String phone;

    @Valid
    @UniqueElements
    private List<CalendarEvent> calendarEvent;

    @Data
    @ScriptAssert(lang = "javascript", script = "_this.startDate.before(_this.endDate)",
            message = "startDate before endDate")
    private class CalendarEvent {

        private Date startDate;

        private Date endDate;

    }

    // 数值检查: @Min、@Max、@Range、@DecimalMin、@DecimalMax、@Digits

    @Min(value = 0, message = "min score is 0")
    @Max(value = 100, message = "max score is 100")
    @Range(min = 0, max = 100, message = "min score is 0 && max score is 100")
    private Integer score;

    @DecimalMin(value = "10.01", inclusive = false, message = "Money 最小值大于10.01")
    @DecimalMax(value = "199.99", message = "Money 最大值小于等于199.99")
    @Digits(integer = 3, fraction = 2, message = "整数位最多为3，小数位最多为2")
    private BigDecimal money;

}

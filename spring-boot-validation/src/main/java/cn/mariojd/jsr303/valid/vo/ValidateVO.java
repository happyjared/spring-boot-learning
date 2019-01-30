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
public class ValidateVO {

    // 空和非空检查: @Null、@NotNull、@NotBlank、@NotEmpty

    @Null(message = "验证是否为 null")
    private Integer isNull;

    @NotNull(message = "验证是否不为 null, 但无法查检长度为0的空字符串")
    private Integer id;

    @NotBlank(message = "检查字符串是不是为 null，以及去除空格后长度是否大于0")
    private String name;

    @NotEmpty(message = "检查是否为 NULL 或者是 EMPTY")
    private List<String> stringList;

    // Boolean值检查: @AssertTrue、@AssertFalse

    @AssertTrue(message = " 验证 Boolean参数是否为 true")
    private Boolean isTrue;

    @AssertFalse(message = "验证 Boolean 参数是否为 false ")
    private Boolean isFalse;

    // 长度检查: @Size、@Length

    @Size(min = 1, max = 2, message = "验证（Array,Collection,Map,String）长度是否在给定范围内")
    private List<Integer> integerList;

    @Length(min = 8, max = 30, message = "验证字符串长度是否在给定范围内")
    private String address;

    // 日期检查: @Future、@FutureOrPresent、@Past、@PastOrPresent

    @Future(message = "验证日期是否在当前时间之后")
    private Date futureDate;

    @FutureOrPresent(message = "验证日期是否为当前时间或之后")
    private Date futureOrPresentDate;

    @Past(message = "验证日期是否在当前时间之前")
    private Date pastDate;

    @PastOrPresent(message = "验证日期是否为当前时间或之前")
    private Date pastOrPresentDate;

    // 其它检查: @Email、@CreditCardNumber、@URL、@Pattern、@ScriptAssert、@UniqueElements

    @Email(message = "校验是否为正确的邮箱格式")
    private String email;

    @CreditCardNumber(message = "校验是否为正确的信用卡号")
    private String creditCardNumber;

    @URL(protocol = "http", host = "127.0.0.1", port = 8080, message = "校验是否为正确的URL地址")
    private String url;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "正则校验是否为正确的手机号")
    private String phone;

    // 对关联对象元素进行递归校验检查

    @Valid
    @UniqueElements(message = "校验集合中的元素是否唯一")
    private List<CalendarEvent> calendarEvent;

    @Data
    @ScriptAssert(lang = "javascript", script = "_this.startDate.before(_this.endDate)",
            message = "通过脚本表达式校验参数")
    private class CalendarEvent {

        private Date startDate;

        private Date endDate;

    }

    // 数值检查: @Min、@Max、@Range、@DecimalMin、@DecimalMax、@Digits

    @Min(value = 0, message = "验证数值是否大于等于指定值")
    @Max(value = 100, message = "验证数值是否小于等于指定值")
    @Range(min = 0, max = 100, message = "验证数值是否在指定值区间范围内")
    private Integer score;

    @DecimalMin(value = "10.01", inclusive = false, message = "验证数值是否大于等于指定值")
    @DecimalMax(value = "199.99", message = "验证数值是否小于等于指定值")
    @Digits(integer = 3, fraction = 2, message = "限制整数位最多为3，小数位最多为2")
    private BigDecimal money;

}

package cn.mariojd.jsr303.validate.controller;

import cn.mariojd.jsr303.validate.vo.Validate;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jared
 * @date 2019/1/15 10:22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("validate")
public class ValidateController {

    /**
     * Valid注解标明要对参数对象进行数据校验
     */
    @PutMapping
    @PostMapping
    public Map<String, Object> test01(@RequestBody @Valid Validate validate, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>(4);

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            map.put("errorMsg", errorMsg);
        }

        map.put("params", validate.toString());
        return map;
    }

    @GetMapping
    @DeleteMapping
    public Map<String, Object> test02(@NotNull(message = "id不能为空") @Range(min = 1, max = 100, message = "id最小为1最大为100") Integer id,
                                      @NotBlank(message = "email不能为空") @Email(message = "邮箱格式错误") String email,
                                      @ModelAttribute @Valid Validate validate) {
        Map<String, Object> map = new HashMap<>(4);

        map.put("id", id);
        map.put("email", email);
        map.put("params", validate.toString());

        return map;
    }

}

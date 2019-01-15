package cn.mariojd.jsr303.valid.controller;

import cn.mariojd.jsr303.valid.vo.ValidVO;
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
@RequestMapping("valid")
public class ValidController {

    @PostMapping
    public Object test01(@Valid ValidVO validVO, BindingResult bindingResult) {
        String result = "";
        if (bindingResult.hasErrors()) {
            result = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
        }
        validVO.setResult(result);
        return validVO;
    }

    @GetMapping
    public Object test02(
                         @Range(min = 1, max = 100, message = "id最小为1最大为100") int id,
                         @NotBlank(message = "email不能为空") @Email(message = "邮箱格式错误") String email) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id", id);
//        map.put("score", score);
        map.put("email", email);
        return map;
    }

}

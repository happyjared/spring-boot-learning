package cn.mariojd.jsr303.valid.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jared
 * @date 2019/1/15 15:07
 */
@Data
public class AttributeVO {

    @NotNull
    private Integer id;

    private String name;

}

package cn.mariojd.webflux.entity;

import cn.mariojd.webflux.enums.OriginEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jared
 * @date 2019/3/29 8:48
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

    @Id
    private String id;

    @NotBlank(message = "品牌不能为空")
    private String brand;

    @NotNull(message = "价格不能为空")
    private Money price;

    @NotNull(message = "产地不能为空")
    private OriginEnum origin;

}

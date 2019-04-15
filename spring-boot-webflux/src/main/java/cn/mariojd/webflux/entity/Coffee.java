package cn.mariojd.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jared
 * @date 2019/3/29 9:12
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    @Id
    private String id;

    @NotBlank(message = "Name is not null")
    private String name;

    @NotNull(message = "Price is not null")
    private Money price;

}

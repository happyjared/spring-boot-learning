package cn.mariojd.data.rest.entity;

import cn.mariojd.data.rest.base.BaseEntity;
import cn.mariojd.data.rest.enums.Gender;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jared
 * @date 2019/4/2 8:58
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    @NotBlank
    private String name;

    @NotNull
    @Enumerated
    private Gender gender;

}

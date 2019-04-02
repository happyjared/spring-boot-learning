package cn.mariojd.data.rest.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Jared
 * @date 2019/4/2 9:00
 */
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createTime;

    @Version
    private Long version;

    @NotNull
    private Boolean deleted = false;

}

package cn.mariojd.springboot.multiple.mongo.datasource.mongo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Jared
 * @date 2019/1/12 13:55
 */
@Data
@Builder
@Document(value = "tb_cartoon")
public class Cartoon {

    @Id
    private String id;

    @Indexed
    private String name;

}

package cn.mariojd.springboot.multiple.datasource.mybatis.postgres.mapper;

import cn.mariojd.springboot.multiple.datasource.mybatis.postgres.entity.Girl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author Jared
 * @date 2018/11/21 16:34
 */
public interface GirlMapper {

    @Insert("CREATE TABLE \"girl\"(\"id\" int4 NOT NULL,\"name\" varchar(255));")
    void createTable();

    @Insert("DROP TABLE IF EXISTS \"girl\";")
    void dropTable();

    @Insert("INSERT INTO girl(id,name) VALUES(#{girl.id},#{girl.name})")
    @Options(useGeneratedKeys = true, keyProperty = "girl.id")
    int insert(@Param("girl") Girl girl);

    @Select("SELECT id,name FROM girl")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
    })
    List<Girl> findAll();

}

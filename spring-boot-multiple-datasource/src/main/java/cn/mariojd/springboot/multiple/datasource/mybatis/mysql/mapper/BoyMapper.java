package cn.mariojd.springboot.multiple.datasource.mybatis.mysql.mapper;

import cn.mariojd.springboot.multiple.datasource.mybatis.mysql.entity.Boy;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author Jared
 * @date 2018/11/21 16:30
 */
public interface BoyMapper {

    @Insert("CREATE TABLE `boy`(`id` int(11) NOT NULL,`name` varchar(255));")
    void createTable();

    @Insert("DROP TABLE IF EXISTS `boy`;")
    void dropTable();

    @Insert("INSERT INTO boy(id,name) VALUES(#{boy.id},#{boy.name})")
    @Options(useGeneratedKeys = true, keyProperty = "boy.id")
    int insert(@Param("boy") Boy boy);

    @Select("SELECT id,name FROM boy")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
    })
    List<Boy> findAll();

}

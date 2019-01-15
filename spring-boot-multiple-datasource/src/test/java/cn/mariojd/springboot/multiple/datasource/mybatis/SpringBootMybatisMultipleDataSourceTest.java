package cn.mariojd.springboot.multiple.datasource.mybatis;

import cn.mariojd.springboot.multiple.datasource.mybatis.mysql.entity.Boy;
import cn.mariojd.springboot.multiple.datasource.mybatis.mysql.mapper.BoyMapper;
import cn.mariojd.springboot.multiple.datasource.mybatis.postgres.entity.Girl;
import cn.mariojd.springboot.multiple.datasource.mybatis.postgres.mapper.GirlMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/11/22 23:15
 * @blog: https://blog.mariojd.cn/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisMultipleDataSourceTest {

    @Resource
    private BoyMapper boyMapper;

    @Resource
    private GirlMapper girlMapper;

    @Before
    public void createTable() {
        boyMapper.dropTable();
        girlMapper.dropTable();
        boyMapper.createTable();
        girlMapper.createTable();
    }

    @Test
    public void test() {
        boyMapper.insert(new Boy(1, "大肖"));
        boyMapper.insert(new Boy(2, "大熊"));
        Assert.assertEquals(2, boyMapper.findAll().size());

        girlMapper.insert(new Girl(1, "小红"));
        girlMapper.insert(new Girl(2, "小花"));
        Assert.assertEquals(2, girlMapper.findAll().size());
    }

}

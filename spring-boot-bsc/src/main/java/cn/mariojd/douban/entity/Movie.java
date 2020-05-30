package cn.mariojd.douban.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jared
 * @date 2020/5/25 16:18
 */
@Data
@Entity
@Table(name = "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 名称
     */
    private String name;

    /**
     * 剧情简介
     */
    private String desc;

    /**
     * 引用
     */
    private String quote;

    /**
     * 上映年份
     */
    @Column(name = "release_year")
    private Integer releaseYear;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评分人次
     */
    @Column(name = "score_members")
    private Integer scoreMembers;

    /**
     * 语言
     */
    private String language;

    /**
     * 类型
     */
    @Column(name = "movie_type")
    private String movieType;

    /**
     * 时长：分钟
     */
    private Integer duration;

    /**
     * 简介头图
     */
    @Column(name = "intro_pic")
    private String introPic;

    /**
     * 豆瓣链接
     */
    private String link;

    /**
     * 头图路径
     */
    private String path;

    /**
     * 观看状态
     */
    private Boolean status;

    /**
     * 观看日期
     */
    @Column(name = "mark_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date markTime;

//    /**
//     * 更新时间
//     */
//    @LastModifiedDate
//    @Column(name = "update_time", nullable = false)
//    private Date updateTime;
//
//    /**
//     * 创建时间
//     */
//    @CreatedDate
//    @Column(name = "create_time", updatable = false, nullable = false)
//    private Date createTime;

}

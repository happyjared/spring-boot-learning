package cn.mariojd.bsc.vo;

import lombok.Data;

/**
 * @author Jared
 * @date 2019/8/9 15:58
 */
@Data
public class Rank {

    /**
     * 阅读量
     */
    private Integer read;

    /**
     * 点赞数
     */
    private Integer like;

    /**
     * 评论量
     */
    private Integer comment;

    /**
     * 简书钻
     */
    private Float jsz;

    /**
     * 总系数
     */
    private Float coefficient;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 文章链接
     */
    private String article;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户主页
     */
    private String home;

    /**
     * 原先排名
     */
    private Integer oldRank;

    /**
     * 当前排名
     */
    private Integer newRank;

}

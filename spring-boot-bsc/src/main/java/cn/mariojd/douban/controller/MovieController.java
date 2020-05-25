package cn.mariojd.douban.controller;

import cn.mariojd.douban.entity.Movie;
import cn.mariojd.douban.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Jared
 * @date 2020/5/25 16:21
 */
@Slf4j
@RestController
@RequestMapping("douban/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @PostMapping("top250")
    public List<Movie> top250() {
        return movieService.top250();
    }


}

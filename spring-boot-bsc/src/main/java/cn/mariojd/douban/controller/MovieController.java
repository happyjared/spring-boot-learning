package cn.mariojd.douban.controller;

import cn.mariojd.douban.entity.Movie;
import cn.mariojd.douban.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("top250")
    public Page<Movie> top250(@PageableDefault(size = 250, sort = "sort", direction = Sort.Direction.ASC) Pageable pageable) {
        return movieService.top250(pageable);
    }

}

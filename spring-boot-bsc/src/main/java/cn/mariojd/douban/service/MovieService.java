package cn.mariojd.douban.service;

import cn.mariojd.douban.entity.Movie;
import cn.mariojd.douban.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jared
 * @date 2020/5/25 16:23
 */
@Slf4j
@Service
public class MovieService {

    @Resource
    private MovieRepository movieRepository;

    public Page<Movie> top250(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

}

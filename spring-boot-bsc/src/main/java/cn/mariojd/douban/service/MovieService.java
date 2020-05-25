package cn.mariojd.douban.service;

import cn.mariojd.douban.entity.Movie;
import cn.mariojd.douban.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
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

    private static final String KEY = "DOUBAN_MOVIE_TOP250";

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private ZSetOperations<String, Movie> zSetOperations;

    public List<Movie> top250() {
//        Set<Movie> movies = zSetOperations.range(KEY, 0, -1);
//        if (CollectionUtils.isEmpty(movies)) {
//            movies = new HashSet<>(movieRepository.findAll());
//            for (Movie movie : movieList) {
//                zSetOperations.add(KEY, movie, movie.getScore().doubleValue());
//            }
//        }
//        return movieList;
        return null;
    }

}

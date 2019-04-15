package cn.mariojd.webflux.service;

import cn.mariojd.webflux.base.BaseResponse;
import cn.mariojd.webflux.entity.Apple;
import cn.mariojd.webflux.repository.AppleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/3/29 8:50
 */
@Slf4j
@Service
public class AppleService {

    @Resource
    private AppleRepository appleRepository;

    public Flux<Apple> getAll() {
        return appleRepository.findAll();
    }

    public Mono<Page<Apple>> getPage(Pageable pageable) {
        return Mono.justOrEmpty(appleRepository.findAll(pageable));
    }

    public Mono<BaseResponse<Page<Apple>>> getPage2(Pageable pageable) {
        return Mono.justOrEmpty(appleRepository.findAll(pageable)).map(BaseResponse::new);
    }

    public Mono<Apple> get(String id) {
        return appleRepository.findById(id);
    }

    public Mono<Apple> add(Apple apple) {
        return appleRepository.save(apple);
    }

    public Mono<Apple> update(Apple apple) {
        return appleRepository.findById(apple.getId()).doOnSuccess(oldApple -> {
            BeanUtils.copyProperties(apple, oldApple);
            appleRepository.save(oldApple);
        });
    }

    public Mono<Apple> delete(String id) {
        return appleRepository.findById(id).doOnSuccess(apple -> appleRepository.delete(apple));
    }

}

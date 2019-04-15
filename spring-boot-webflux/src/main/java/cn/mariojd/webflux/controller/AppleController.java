package cn.mariojd.webflux.controller;

import cn.mariojd.webflux.base.BaseResponse;
import cn.mariojd.webflux.entity.Apple;
import cn.mariojd.webflux.service.AppleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Jared
 * @date 2019/3/29 8:50
 */
@RestController
@RequestMapping("user")
public class AppleController {

    @Resource
    private AppleService appleService;

    @GetMapping
    public Flux<Apple> getAll() {
        return appleService.getAll();
    }

    @GetMapping("page")
    public Mono<Page<Apple>> getPage(Pageable pageable) {
        return appleService.getPage(pageable);
    }

    @GetMapping("page2")
    public Mono<BaseResponse<Page<Apple>>> getPage2(Pageable pageable) {
        return appleService.getPage2(pageable);
    }

    @GetMapping("{id}")
    public Mono<Apple> get(@PathVariable String id) {
        return appleService.get(id);
    }

    @PostMapping
    public Mono<Apple> add(@RequestBody @Valid Apple apple) {
        return appleService.add(apple);
    }

    @PutMapping
    public Mono<Apple> update(@RequestBody @Valid Apple apple) {
        return appleService.update(apple);
    }

    @DeleteMapping("{id}")
    public Mono<Apple> delete(@PathVariable String id) {
        return appleService.delete(id);
    }

}

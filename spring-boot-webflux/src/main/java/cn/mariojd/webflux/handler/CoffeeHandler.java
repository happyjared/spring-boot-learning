package cn.mariojd.webflux.handler;

import cn.mariojd.webflux.entity.Coffee;
import cn.mariojd.webflux.repository.CoffeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/3/29 9:13
 */
@Component
public class CoffeeHandler {

    @Resource
    private CoffeeRepository coffeeRepository;

//    public Mono<ServerResponse> get(ServerRequest request) {
//        int id = Integer.parseInt(request.pathVariable("id"));
//        final Mono<Coffee> people = coffeeRepository.findById(id);
//        return people
//                .flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromPublisher(people, Coffee.class)))
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
//
//    public Mono<ServerResponse> save(Coffee coffee) {
//        Mono<ServerResponse> objectMono = Mono.create(coffeeSink -> coffeeSink.success(coffeeRepository.save(coffee)));
//        return ;
//    }

}

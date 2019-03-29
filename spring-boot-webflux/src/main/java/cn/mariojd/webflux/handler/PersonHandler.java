package cn.mariojd.webflux.handler;

import cn.mariojd.webflux.entity.People;
import cn.mariojd.webflux.repository.PeopleRepository;
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
public class PersonHandler {

    @Resource
    private PeopleRepository peopleRepository;

    public Mono<ServerResponse> get(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        final Mono<People> people = peopleRepository.findById(id);
        return people
                .flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(people, People.class)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}

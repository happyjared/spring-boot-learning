package cn.mariojd.webflux.router;

import cn.mariojd.webflux.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Jared
 * @date 2019/3/29 9:12
 */
@Configuration
public class PersonRouter {

    /**
     * 创建Person路由函数
     */
    @Bean
    public RouterFunction<ServerResponse> router(PersonHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/people/{id}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::get);
    }

}

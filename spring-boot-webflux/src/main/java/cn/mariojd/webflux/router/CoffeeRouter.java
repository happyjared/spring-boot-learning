package cn.mariojd.webflux.router;

import cn.mariojd.webflux.handler.CoffeeHandler;
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
public class CoffeeRouter {

    /**
     * 创建Coffee路由函数
     */
    @Bean
    public RouterFunction<ServerResponse> router(CoffeeHandler handler) {
        return null;
        //        return RouterFunctions
//                .route(RequestPredicates.GET("people/{id}")
//                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::get);
//                .andRoute(RequestPredicates.POST("people")
//                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::save)
//                .andRoute(RequestPredicates.PUT("people")
//                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::get)
//                .andRoute(RequestPredicates.DELETE("people/{id}")
//                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::get);
    }

}

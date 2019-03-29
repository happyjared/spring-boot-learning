package cn.mariojd.webflux.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author Jared
 * @date 2019/3/29 9:34
 */
public class GlobalException extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        map.put("code", HttpStatus.BAD_REQUEST);
        map.put("msg", "参数缺失");
        return map;
    }
}

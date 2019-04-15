package cn.mariojd.webflux.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jared
 * @date 2019/4/1 16:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public BaseResponse(T data) {
        this.code = 0;
        this.data = data;
        this.msg = "SUCCESS";
    }
}

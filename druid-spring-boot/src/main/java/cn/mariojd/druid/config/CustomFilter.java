package cn.mariojd.druid.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author Jared
 * @date 2019/4/15 14:03
 */
@Slf4j
public class CustomFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("connect before...");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("connect after...");
    }

}

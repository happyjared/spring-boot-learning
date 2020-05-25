package cn.mariojd.bsc.controller;

import cn.hutool.json.JSONUtil;
import cn.mariojd.common.constant.BscConstant;
import cn.mariojd.common.util.IpUtil;
import cn.mariojd.bsc.vo.Rank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Jared
 * @date 2020/5/25 16:10
 */
@Slf4j
@RestController
public class BscController {

    private AtomicInteger atomicInteger;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void initGetCount() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String count = ops.get(BscConstant.COUNT);
        if (StringUtils.isEmpty(count)) {
            count = "0";
            ops.set(BscConstant.COUNT, count);
        }
        atomicInteger = new AtomicInteger(Integer.parseInt(count));
    }

    @PostMapping("rank")
    public List<Rank> rank(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        int count = atomicInteger.addAndGet(1);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.increment(BscConstant.COUNT);
        String ipAddress = IpUtil.getIpAddr(request);
        if (!StringUtils.isEmpty(ipAddress)) {
            SetOperations<String, String> set = stringRedisTemplate.opsForSet();
            set.add(BscConstant.IP, ipAddress);
        }

        List<Rank> rankList;
        String strRank = ops.get(BscConstant.RANK_CACHE);
        if (StringUtils.isEmpty(strRank)) {
            log.info("===> 排名缓存未命中，重新获取中...");
            ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
            Set<String> ranks = zSet.reverseRange(BscConstant.ARTICLES_SCORE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (Objects.nonNull(ranks) && ranks.size() > 0) {
                HashOperations<String, String, Object> hash = stringRedisTemplate.opsForHash();
                rankList = ranks.stream().map(article -> {
                    Map<String, Object> map = hash.entries(article);
                    map.put("article", article);
                    return JSONUtil.toBean(JSONUtil.toJsonStr(map), Rank.class);
                }).collect(Collectors.toList());
                ops.set(BscConstant.RANK_CACHE, JSONUtil.toJsonStr(rankList), 3, TimeUnit.HOURS);
            } else {
                rankList = Collections.emptyList();
            }
        } else {
            rankList = JSONUtil.toList(JSONUtil.parseArray(strRank), Rank.class);
        }
        long spend = System.currentTimeMillis() - start;
        log.info("===> 第{}次访问，IP：{}，耗时：{}ms", count, ipAddress, spend);
        return rankList;
    }

}

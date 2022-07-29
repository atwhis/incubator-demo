package com.ymchen.incubatordemo.examples.designmodel.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisRepository implements Repository {

    @Override
    public void save(Object object) {
      log.info("redisRepository save.....");
    }
}

package com.ymchen.incubatordemo.examples.designmodel.factory;

import java.util.Objects;

public class RepositoryFactory {

    public static Repository getRepository(String repositoryName) {
        Repository result = null;
        switch (Objects.requireNonNull(RepositoryEnum.getByName(repositoryName))) {
            case JDBC:
                result = new JdbcRepository();
                break;
            case ZK:
                result = new ZKRepository();
                break;
            case REDIS:
                result = new RedisRepository();
                break;
        }
        return result;
    }
}

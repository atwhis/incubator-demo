package com.ymchen.incubatordemo.examples.designmodel.factory;

public class FactoryExample {
    public static void main(String[] args) {

        Repository jdbc = RepositoryFactory.getRepository(RepositoryEnum.JDBC.getRepositoryName());
        jdbc.save(new Object());

        Repository redis = RepositoryFactory.getRepository(RepositoryEnum.REDIS.getRepositoryName());
        redis.save(new Object());

        Repository zk = RepositoryFactory.getRepository(RepositoryEnum.ZK.getRepositoryName());
        zk.save(new Object());
    }
}

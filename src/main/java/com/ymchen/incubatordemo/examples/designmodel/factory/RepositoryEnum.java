package com.ymchen.incubatordemo.examples.designmodel.factory;


public enum RepositoryEnum {
    JDBC("jdbc", "数据库"),
    REDIS("redis", "缓存"),
    ZK("zk", "zookeeper");


    public static RepositoryEnum getByName(String repositoryName) {
        for (RepositoryEnum repositoryEnum : RepositoryEnum.values()) {
            if (repositoryEnum.getRepositoryName().equals(repositoryName)) {
                return repositoryEnum;
            }
        }

        return null;
    }

    RepositoryEnum(String repositoryName, String repositoryDesc) {
        this.repositoryName = repositoryName;
        this.repositoryDesc = repositoryDesc;
    }

    private String repositoryName;

    private String repositoryDesc;


    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryDesc() {
        return repositoryDesc;
    }
}

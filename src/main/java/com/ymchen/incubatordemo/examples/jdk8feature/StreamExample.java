package com.ymchen.incubatordemo.examples.jdk8feature;

import com.ymchen.incubatordemo.common.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class StreamExample {


    /**
     * +--------------------+       +------+   +------+   +---+   +-------+
     * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
     * +--------------------+       +------+   +------+   +---+   +-------+
     *
     * @param args
     */

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("aa", "cc", "ff", "dd", "zz", "ss", "mm", "", "tt", "zz");
        List<Integer> integerList = Arrays.asList(3, 5, 7, 22, 10, 21, 2);


        IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
        log.info("intList 最大值:{}", intSummaryStatistics.getMax());
        log.info("intList 平均值:{}", intSummaryStatistics.getAverage());
        log.info("intList 总和:{}", intSummaryStatistics.getCount());
        log.info("intList 排序:");
        integerList.stream().sorted().forEach(StreamExample::printByTab);

        System.out.println();
        log.info("strList 长度:{}", strList.size());
        log.info("strList 中空字符串数量:{}", strList.stream().filter(String::isEmpty).count());
        log.info("strList 去空去重排序取前5");
        strList.stream().filter(str -> !str.isEmpty()).distinct().sorted().limit(5).forEach(StreamExample::printByTab);

        System.out.println();
        log.info("map 迭代");
        getCarMapInfo().forEach((k, v) -> System.out.print("key:" + k + " value:" + v + "\t"));


        log.info("年龄大于20的用户:{}", getUserList().stream().filter(user -> user.getAge() >= 20).findFirst().get());
        System.out.println();
        log.info("姓名为张三的用户信息:");
        getUserList().stream().filter(user -> user.getUserName().equals("zhangSan"))
                .collect(Collectors.toList()).forEach(System.out::println);


        log.info("用户年龄总和:{}", getUserList().stream().mapToInt(User::getAge).sum());

        log.info("成年用户信息:");
        List<User> adults = getUserList().stream().filter(user -> user.getAge() > 18).collect(Collectors.toList());
        adults.forEach(System.out::println);


    }

    private static void printByTab(Object obj) {
        System.out.print(obj + "\t");
    }

    private static Map<String, Object> getCarMapInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "levin");
        map.put("color", "white");
        map.put("model", "2021 xx型");
        map.put("price", 110000.100);
        return map;
    }

    private static List<User> getUserList() {
        User user1 = User.builder().userName("zhangSan").age(18).build();
        User user2 = User.builder().userName("liSi").age(22).build();
        User user3 = User.builder().userName("liuBei").age(19).build();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
}

package com.ymchen.incubatordemo.examples.jdk8feature;

import com.ymchen.incubatordemo.common.model.Calculator;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class FunctionExample {

    static Integer calcul(Integer num, Function<Integer, Integer> function) {
        return function.apply(num);
    }

    static Integer calcul(Integer num1, Integer num2, BiFunction<Integer, Integer, Integer> function) {
        return function.apply(num1, num2);
    }


    public static void main(String[] args) {
        Function<Integer, Integer> multiply = num -> {
            return num * num;
        };

        log.info("3*3 : {}", calcul(3, num -> num * num));
        log.info("3+3 : {}", calcul(3, 3, Integer::sum));
        log.info("5*5 : {}", multiply.apply(5));


        Calculator add = Double::sum;
        Calculator multi = (double x, double y) -> {
            return x * y;
        };
        Calculator max = Double::max;

        log.info("3+4: {}", add.calculator(3, 4));
        log.info("3*4: {}", multi.calculator(3, 4));
        log.info("3 and 4 max: {}", max.calculator(3, 4));
    }
}

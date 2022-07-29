package com.ymchen.incubatordemo.examples.springel;

import com.ymchen.incubatordemo.common.model.Movie;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;

public class SpringElDemo {
    public static void main(String[] args) {

        ExpressionParser expressionParser = new SpelExpressionParser();
        System.out.println(expressionParser.parseExpression("123456").getValue());
        System.out.println(expressionParser.parseExpression("true").getValue());
        System.out.println(expressionParser.parseExpression("{'1','2','3'}").getValue(List.class));

        TemplateParserContext templateParserContext = new TemplateParserContext("{", "}");

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setVariable("name", "张三");
        standardEvaluationContext.setVariable("isAdult", false);
        standardEvaluationContext.setVariable("age", 17);
        System.out.println(expressionParser.parseExpression("{#name},{#isAdult},{#age}", templateParserContext).getValue(standardEvaluationContext));

        Movie movie = new Movie(1, "少林足球", "喜剧", 8.9);
        standardEvaluationContext.setVariable("movie", movie);
        System.out.println(expressionParser.parseExpression("movie:{#movie.name}---score:{#movie.score}", templateParserContext).getValue(standardEvaluationContext, String.class));

        Movie movie11 = new Movie(11, "功夫", "喜剧", 8.7);
        Movie movie22 = new Movie(12, "九品芝麻官", "喜剧", 9.0);
        List<Movie> movies = Arrays.asList(movie11, movie22);
        standardEvaluationContext.setVariable("movies", movies);
        System.out.println(expressionParser.parseExpression("{#movies[0].name},{#movies[0].score}", templateParserContext).getValue(standardEvaluationContext));
    }
}

package com.ymchen.external;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * test spring default component scan path
 */
@Component
@Slf4j
public class MovieService {

    public void getMovieInfo() {
        log.info("movie service .... get movie info");
    }
}

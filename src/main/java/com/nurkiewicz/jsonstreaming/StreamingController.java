package com.nurkiewicz.jsonstreaming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class StreamingController {

    @GetMapping(value = "/sse", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<Data> sse() {
        return source();
    }

    @GetMapping(value = "/ndjson", produces = "application/x-ndjson")
    Flux<Data> ndjson() {
        return source();
    }

    @GetMapping(value = "/array")
    Flux<Data> array() {
        return source();
    }

    Flux<Data> source() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> new Data(i, Instant.now()));
    }

}



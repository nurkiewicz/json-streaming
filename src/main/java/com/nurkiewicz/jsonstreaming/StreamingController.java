package com.nurkiewicz.jsonstreaming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class StreamingController {

    @GetMapping(value = "/sse", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<Data> sse(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        return source(fail);
    }

    @GetMapping(value = "/ndjson", produces = "application/x-ndjson")
    Flux<Data> ndjson(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        return source(fail);
    }

    @GetMapping(value = "/array")
    Flux<Data> array(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        return source(fail);
    }

    Flux<Data> source(boolean fail) {
        return fail ? failing() : successful();
    }

    Flux<Data> failing() {
        return successful()
                .concatWith(Mono.error(new RuntimeException("Opps!")));
    }

    private Flux<Data> successful() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> new Data(i, Instant.now()));
    }

}



package com.xaero.dictionary.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.lang.System.exit;
import static java.text.MessageFormat.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.boot.SpringApplication.exit;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
public class ShutdownController implements ApplicationContextAware {

    private static final int THREAD_COUNT = 1;

    private static final long SHUTDOWN_DELAY = 5L;
    private static final int EXIT_STATUS = 0;

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @GetMapping(path = "/shutdown", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> shutdown() {
        log.info("got a shutdown request. stop application after {} seconds", SHUTDOWN_DELAY);
        new ScheduledThreadPoolExecutor(THREAD_COUNT).schedule(() -> {
            exit(context, () -> EXIT_STATUS);
            exit(EXIT_STATUS);
        }, SHUTDOWN_DELAY, SECONDS);

        return ok(format("The application will shut down in {0} seconds", SHUTDOWN_DELAY));
    }
}

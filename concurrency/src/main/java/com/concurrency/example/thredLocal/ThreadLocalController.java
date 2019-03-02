package com.concurrency.example.thredLocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/threadLocal")
public class ThreadLocalController {

    @GetMapping(value = "/test")
    public Long test() {
        return RequestHolder.getId();
    }
}

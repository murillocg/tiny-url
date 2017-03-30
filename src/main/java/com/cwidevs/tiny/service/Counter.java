package com.cwidevs.tiny.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 *
 * @author murillo.goulart
 */
@Service
public class Counter {
    
    private static final long INITIAL_VALUE = 10000000L;
    
    private static final String COUNTER_KEY = "tiny-url:counter";
    
    private AtomicLong counter = new AtomicLong(INITIAL_VALUE);
    
    public Long next() {
        return counter.incrementAndGet();
    }
}

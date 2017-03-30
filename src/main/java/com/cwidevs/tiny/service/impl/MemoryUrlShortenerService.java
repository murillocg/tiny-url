package com.cwidevs.tiny.service.impl;

import com.cwidevs.tiny.service.Counter;
import com.cwidevs.tiny.service.UrlShortenerService;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author murillo.goulart
 */
@Service
public class MemoryUrlShortenerService implements UrlShortenerService {

    private final Map<String, String> store = new HashMap<>();
    
    @Autowired
    private Counter counter;
    
    @Override
    public String create(URL url) {
        Long nextValue = counter.next();
        String key = "url-" + nextValue.toString();
        store.put(key, url.toString());
        return nextValue.toString();
    }

    @Override
    public Optional<String> get(String id) {
        Long value = Long.valueOf(id);
        String key = "url-" + value.toString();
        return Optional.ofNullable(store.get(key));
    }
    
}

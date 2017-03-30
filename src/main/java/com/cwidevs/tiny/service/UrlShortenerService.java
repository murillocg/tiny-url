package com.cwidevs.tiny.service;

import java.net.URL;
import java.util.Optional;

/**
 *
 * @author murillo.goulart
 */
public interface UrlShortenerService {
    
    public String create(URL url);
    
    public Optional<String> get(String id);
    
}

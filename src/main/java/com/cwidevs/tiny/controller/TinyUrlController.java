package com.cwidevs.tiny.controller;

import com.cwidevs.tiny.exception.ResourceNotFoundException;
import com.cwidevs.tiny.service.UrlShortenerService;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author murillo.goulart
 */
@RestController
public class TinyUrlController {

    private final Logger log = LoggerFactory.getLogger(TinyUrlController.class);
    
    @Autowired
    private UrlShortenerService urlShortenerService;
    
    @PostMapping("/url")
    public ResponseEntity<Void> create(@RequestBody String url) throws MalformedURLException, URISyntaxException {
        URL longUrl = new URL(url);
        log.info("Creating shortened URL for: {}", url);
        String path = urlShortenerService.create(longUrl);        
        return ResponseEntity.created(new URI("/" + path)).build();
    }
    
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable String id) {
        Optional<String> longUrl = urlShortenerService.get(id);
        if (!longUrl.isPresent()) {
            throw new ResourceNotFoundException();
        }
        log.info("Redirecting to resolved URL for id: {} -> {}", id, longUrl.get());
        final RedirectView redirectView = new RedirectView(longUrl.get());
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(redirectView);
    }
}

package com.example.urlshortner;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author bruno.alves.rocha
 */

@Service
public class LinkService {

    private final String baseUrl;

    private final LinkRepository linkRepository;

    public LinkService(@Value("${app.base.url}") String baseUrl, LinkRepository linkRepository) {
        this.baseUrl = baseUrl;
        this.linkRepository = linkRepository;
    }

    Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(link, randomKey))
                .map(result -> baseUrl + result.getKey());
    }

    public Mono<Link> getOriginalLink(String key) {
        return linkRepository.findByKey(key);
    }
}

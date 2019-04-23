package com.example.urlshortner;

import reactor.core.publisher.Mono;

/**
 * @author bruno.alves.rocha
 */
public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}

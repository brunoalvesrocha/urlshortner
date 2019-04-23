package com.example.urlshortner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bruno.alves.rocha
 */
public class LinkServiceTest {
    
    private LinkRepository linkRepository = mock(LinkRepository.class);
    private LinkService linkService = new LinkService("http://some-domain.com", linkRepository);

    @Before
    public void setup() {
        when(linkRepository.save(any())).thenAnswer(invocationOnMock ->
                Mono.just((Link) invocationOnMock.getArguments()[0]));
    }

    @Test
    public void shortensLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                && result.startsWith("http://some-domain.com"))
                .expectComplete()
                .verify();
    }

}
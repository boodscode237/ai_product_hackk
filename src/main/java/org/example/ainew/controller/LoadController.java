package org.example.ainew.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ainew.service.EmbeddingComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoadController {

    private final EmbeddingComponent embeddingComponent;
    @GetMapping("/loader/single")
    public void loadSingle(){
        log.info("Loading single document start");
        embeddingComponent.loadingSingleDocument();
        log.info("Loading single document  end");

    }
}

package org.example.ainew.controller;

import lombok.RequiredArgsConstructor;
import org.example.ainew.model.BookModel;
import org.example.ainew.service.GeneAIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/")
public class GenerativeController {
    private final GeneAIService genAIService;

    @PostMapping
    public ChatResponse getChatResponse(@RequestBody  ChatRequest request){

        return   new ChatResponse(genAIService.getResponse(request));
    }

    @PostMapping("/book")
    public BookModel getDataEntry(@RequestBody ChatRequest request){
        return  genAIService.getModelDataEntryFromText(request.question());
    }

    @PostMapping("/extend")
    public ChatResponse getChatResponseExtended(@RequestBody  ChatRequest request){

        return   new ChatResponse(genAIService.getResponseExtended(request));
    }
}

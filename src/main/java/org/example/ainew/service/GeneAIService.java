package org.example.ainew.service;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import lombok.RequiredArgsConstructor;
import org.example.ainew.controller.ChatRequest;
import org.example.ainew.model.BookModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneAIService {
    private  final  Assistant assistant;
    private  final  RAGAssistant ragAssistant;

    public String getResponse(ChatRequest request){
        return  assistant.chat(request.userId(), request.question());

    }

    public String getResponseExtended(ChatRequest request){
        return  ragAssistant.chat(request.userId(), request.question());


    }
    public String getResponseSimple(ChatRequest request){
        List<ChatMessage> messages =  new ArrayList<>();
        messages.add(SystemMessage.systemMessage("Respond in Russian"));
        messages.add(UserMessage.userMessage(request.question()));
        var model =  OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4)
                .build();
      return   model.generate(messages).content().text();

    }


    public BookModel getModelDataEntryFromText(String question) {
        var popularGenres = List.of("Fiction", "Mystery", "Romance", "Science Fiction", "Fantasy", "Thriller", "Historical Fiction", "Young Adult", "Non-Fiction", "Biography");
        return  assistant.extractDataEntry(question, popularGenres);
    }
}

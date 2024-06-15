package org.example.ainew.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.example.ainew.model.BookModel;

import java.util.List;

public interface Assistant {
    @SystemMessage(
            """
                    You are a helpful  assistant . Try to respond in a fair and warm manner.
                    If you don't know answer , just tell it.
                    """
    )
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @SystemMessage("Extract information about a book from {{text}} in J=jason format")
    @UserMessage("And genre should be from this {{genreList}}")
    BookModel extractDataEntry(@V("text") String text, @V("genreList")List<String> genres);
}

package org.example.ainew.service;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Component
@AllArgsConstructor
public class EmbeddingComponent {
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore embeddingStore;

    public void loadingSingleDocument(){
        String currentDir = System.getProperty("user.dir");
        String filename = "/News_translated_na_ru_cleaned_1.pdf";
        Document document = loadDocument( currentDir + filename , new ApachePdfBoxDocumentParser());

      EmbeddingStoreIngestor embeddingStoreIngestor =   EmbeddingStoreIngestor .builder()
                .documentSplitter(DocumentSplitters.recursive(300, 10))
                .embeddingModel( embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
      embeddingStoreIngestor.ingest(document);
    }
}

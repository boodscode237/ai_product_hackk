# Text File Question Answering with Relevant URLs and Translation

This repository contains a Python script for setting up a question-answering (QA) system that works with `.pdf` files. The system uses language models to provide answers to user queries and includes relevant URLs from the source documents in the responses. Additionally, it translates the responses to Russian.

## Requirements

Before running the script, ensure you have the following installed:
- Python 3.x
- Necessary Python packages (listed below)

## Installation

You can install the required packages using the following commands:

```python
%%capture
# Install required packages in one go
!pip install -q langchain torch sentence_transformers faiss-cpu huggingface-hub accelerate llama-cpp-python git+https://github.com/huggingface/transformers langchain-community
```

## Setup

1. **Mount Google Drive**:
    - This script is designed to run in Google Colab. It mounts Google Drive to access `.pdf` files stored there.
    
2. **Load Text Files**:
    - The script loads text files from a specified directory in Google Drive.

3. **Text Splitting**:
    - The text documents are split into manageable chunks using `RecursiveCharacterTextSplitter`.

4. **Embeddings and Vector Store**:
    - The script generates embeddings for the text chunks using the `sentence-transformers` model.
    - A FAISS vector store is created from the text chunks and embeddings.

5. **Language Model Initialization**:
    - The script initializes a language model using `LlamaCpp`.

6. **Custom RetrievalQA Class**:
    - A custom `RetrievalQA` class is used to include relevant URLs in the QA responses.

7. **Translation Function**:
    - A function is provided to translate the QA responses to Russian using the `MarianMTModel`.

8. **Interactive Query Loop**:
    - The script enters an interactive loop, allowing users to input queries and receive answers along with source URLs and translations.

## Usage

1. **Mount Google Drive**:
    - Ensure your `.pdf` files are in a directory within your Google Drive.
    - Update the `PyPDFDirectoryLoader` path to point to this directory.

```python
from google.colab import drive
drive.mount('/content/drive')

# Load text files
loader = PyPDFDirectoryLoader("/content/drive/MyDrive/data")
data = loader.load()
```

2. **Run the Script**:
    - Run the script in a Google Colab notebook.
    - The script will load the text files, split them into chunks, generate embeddings, and set up the QA system.

3. **Interactive Loop**:
    - After setup, the script enters an interactive loop where you can input queries.
    - Type your query and press Enter to receive an answer along with source URLs.
    - The answer is also translated into Russian.

```python
import sys

while True:
    user_input = input("Введите запрос: ")
    if user_input.lower() == 'exit':
        print('Выход')
        sys.exit()
    if user_input.strip() == '':
        continue
    result = qa.run({'query': user_input})
    answer_with_urls = format_qa_result(result)
    answer_in_russian = translate_to_russian(answer_with_urls, source_lang='en', target_lang='ru')
    print(f"Ответ: {answer_in_russian}")
```

## Example Query

```text
Вот пять вопросов, которые вы могли бы задать:

1.  Вопрос: "Какова была оценочная стоимость восстановления после разрушений, вызванных тропическим циклоном Габриэль в Новой Зеландии?"
   - Answer.........
      Source: [https://www.inform.kz/cn/11_a4037945/amp](https://www.inform.kz/cn/11_a4037945/amp)

2. Вопрос: "Сколько дней отпуска в общей сложности будет у жителей Харбина в течение месяца Весеннего фестиваля в этом году?"
   - Answer.........
      Source: [https://www.inform.kz/cn/12_a4041294](https://www.inform.kz/cn/12_a4041294)

3. Вопрос: "Какой самый популярный вид цветов, импортируемых из Казахстана, и его количество?"
   - Answer.........
      Source: [https://www.inform.kz/cn/1341_a4042127](https://www.inform.kz/cn/1341_a4042127)

4. Вопрос: "Какие действия объявил премьер-министр Новой Зеландии в ответ на циклон Габриэль?"
   - Answer.........
      Source: [https://www.inform.kz/cn/11_a4037945/amp](https://www.inform.kz/cn/11_a4037945/amp)

5. Вопрос: "Какие конкретные дни являются праздничными днями традиционного казахского фестиваля в течение месяца Весеннего фестиваля?"
   - Answer.........
      Source: [https://www.inform.kz/cn/12_a4041294](https://www.inform.kz/cn/12_a4041294)
```

## Notes

- Ensure that the `model_path` is correctly set to the downloaded model file.
- Adjust the paths and parameters as necessary for your specific use case.

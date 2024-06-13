# from news_reader import read_news_from_txt
# from qa_system import answer_question

# def chat_with_bot(news_data):
#     print("Hello! My name is Yolo. I'm your news chatbot. Ask me anything about the news.")
#     while True:
#         question = input("You: ")
#         if question.lower() in ["exit", "quit", "bye"]:
#             print("Bot: Goodbye!")
#             break
#         answer = answer_question(news_data, question)
#         print(f"Bot: {answer}")

# if __name__ == "__main__":
#     txt_file = 'News_translated_na_ru_cleaned.txt'
#     news_data = read_news_from_txt(txt_file)
#     chat_with_bot(news_data)


# import os
# from news_reader import read_news_from_txt
# from qa_system import answer_question

# os.environ["HF_HUB_DISABLE_SYMLINKS_WARNING"] = "1"

# def chat_with_bot(news_data):
#     print("Привет! Меня зовут Йоло. Я ваш новостной чат-бот. Спрашивайте меня о любых новостях ^_^")
#     while True:
#         question = input("You: ")
#         if question.lower() in ["exit", "quit", "bye"]:
#             print("Bot: Goodbye!")
#             break
#         # print(f"Debug: Received question: {question}")  # Debugging line
#         response = answer_question(news_data, question)
#         answer = response['answer']
#         title = response['title']
#         link = response['link']
#         print(f"Debug: Generated answer: {answer}")  # Debugging line
#         print(f"Bot: {answer}\nTitle: {title}\nLink: {link}")

# if __name__ == "__main__":
#     txt_file = r'G:\Хакатон_01.06.2024\News_translated_na_ru_cleaned.txt'
#     news_data = read_news_from_txt(txt_file)
#     print(f"Debug: Loaded news data with {len(news_data)} items")  # Debugging line
#     chat_with_bot(news_data)

import os
import string
from news_reader import read_news_from_txt
from qa_system import answer_question

os.environ["HF_HUB_DISABLE_SYMLINKS_WARNING"] = "1"

# Function to remove punctuation from a string
def remove_punctuation(text):
    return text.translate(str.maketrans('', '', string.punctuation))

def answer_question_wrapper(news_data, question):
    response = answer_question(news_data, question)
    answer = response['answer']
    title = response['title']
    link = response['link']
    return answer, title, link

def chat_with_bot(news_data):
    print("Привет! Меня зовут Йоло - ваш новостной чат-бот. Очень приятно с вами познакомиться!\nСпрашивайте меня о любых новостях ^_^")
    while True:
        user_input = input("You: ")
        cleaned_input = remove_punctuation(user_input).lower()

        if cleaned_input in ["exit", "quit", "bye"]:
            print("Bot: Goodbye!")
            break
        elif cleaned_input in ["hello", "hi", "hey", "whats up"]:
            print("Bot: Hello! How can I help you?")
        else:
            answer, title, link = answer_question_wrapper(news_data, user_input)
            print(f"Bot: {answer}")
            print("также смотрите:")
            print(f"title: {title}\nlink: {link}")

if __name__ == "__main__":
    txt_file = r'G:\Хакатон_01.06.2024\News_translated_na_ru_cleaned.txt'
    news_data = read_news_from_txt(txt_file)
    chat_with_bot(news_data)
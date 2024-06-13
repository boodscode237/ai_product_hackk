from transformers import pipeline

# 初始化问答模型
qa_pipeline = pipeline("question-answering")

# 示例问答函数
def answer_question(news_data, question):
    for news in news_data:
        result = qa_pipeline(question=question, context=news['text'])
        if result['score'] > 0.1:  # 设定一个阈值，只返回可信度较高的答案
            return result['answer']
    return "Sorry, I don't have an answer for this question."


# from transformers import pipeline

# def answer_question(news_data, question):
#     nlp = pipeline("question-answering", model="distilbert-base-cased-distilled-squad")
#     context = " ".join([news['text'] for news in news_data])
#     result = nlp(question=question, context=context)

#     # 查找包含答案的新闻条目
#     for news in news_data:
#         if result['answer'] in news['text']:
#             return {
#                 'answer': result['answer'],
#                 'title': news['title'],
#                 'link': news['link']
#             }
#     return {
#         'answer': "Sorry, I couldn't find an exact match.",
#         'title': "N/A",
#         'link': "N/A"
#     }


# from transformers import pipeline

# # 创建问答管道
# # nlp = pipeline("question-answering", model="distilbert-base-cased-distilled-squad")

# nlp = pipeline("question-answering")

# # 设置置信度阈值
# CONFIDENCE_THRESHOLD = 0.90

# def answer_question(news_data, question):
#     for news_item in news_data:
#         context = news_item['text']
#         title = news_item['title']
#         link = news_item['link']

#         result = nlp(question=question, context=context)

#         if result['score'] >= CONFIDENCE_THRESHOLD:
#             return {
#                 'answer': result['answer'],
#                 'title': title,
#                 'link': link
#             }

#     # 如果没有找到任何超过阈值的答案，返回一个默认值
#     return {
#         'answer': "Извините, я не смог найти подходящий ответ.",
#         'title': "",
#         'link': ""
#     }
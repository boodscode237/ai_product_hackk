def read_news_from_txt(txt_file):
    with open(txt_file, 'r', encoding='utf-8') as file:
        content = file.read()
    news_items = content.strip().split('\n\n')
    news_data = []
    for item in news_items:
        lines = item.split('\n')
        news_dict = {
            "title": lines[0].replace("Title: ", "").strip(),
            "link": lines[1].replace("Link: ", "").strip(),
            "text": lines[2].replace("Text: ", "").strip()
        }
        news_data.append(news_dict)
    return news_data
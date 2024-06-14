FROM python:3.9.17-slim
RUN pip install pipenv

WORKDIR /model

COPY ["Pipfile", "Pipfile.lock", "./"]

RUN pipenv install --system --deploy

COPY ["*.py", "./"]

EXPOSE 9696

ENTRYPOINT ["gunicorn", "--bind=0.0.0.0:9696", "churn_predict:app"]
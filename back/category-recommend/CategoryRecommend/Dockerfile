FROM python:3
WORKDIR /usr/src/app

COPY ./requirements.txt ./
RUN pip install --upgrade pip
RUN pip install --use-pep517
RUN pip install numpy scipy
RUN pip install scikit-surprise
RUN pip install -r requirements.txt

COPY . .

EXPOSE 8080

CMD ["gunicorn", "--bind", "0.0.0.0:8080", "stock.wsgi:application"]

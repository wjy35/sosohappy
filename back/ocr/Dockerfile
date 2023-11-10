FROM gradle:8.2.1-jdk11 as build
WORKDIR /app

COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./src ./src

RUN gradle build -x test --parallel

FROM azul/zulu-openjdk:11
WORKDIR /app

RUN apt-get -y update
RUN apt -y install wget
RUN apt -y install unzip
RUN apt -y install curl

# Install gnupg to handle GPG keys
RUN apt-get -y install gnupg

# Install the latest version of Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update
RUN apt-get -y install google-chrome-stable

# Create the /usr/local/bin directory and download ChromeDriver for the specified version (e.g., 119.0.6045.105)
RUN mkdir -p /usr/local/bin && \
    CHROMEDRIVER_VERSION=119.0.6045.105 && \
    CHROMEDRIVER_URL=https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/$CHROMEDRIVER_VERSION/linux64/chromedriver-linux64.zip && \
    wget -O /tmp/chromedriver.zip $CHROMEDRIVER_URL && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin && \
    mv /usr/local/bin/chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && \
    chmod +x /usr/local/bin/chromedriver

COPY --from=build /app/build/libs/*.jar ./app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]

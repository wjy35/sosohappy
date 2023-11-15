## 배포시 특이사항
1. msa구조로 각 서버의 docker-compose.yml 을 첨부합니다
2. 해당 내용은 환경변수/네트워크 정보를 포함하고 있습니다.
3. api-gateway는 ssl 인증서를 별도로 resource에 복사해야합니다.
4. kafka는 3개의 노드로 클러스팅 했으며 topic list를 작성했습니다.
5. kafka debezium은 connectro를 3개의 노드로 분산환경에서 실행했으며 mysql connector를 설치해야 합니다.

## Version
```text
Java 11
Spring Boot 2.7.17
Mysql 8.0.22
Redis 7.2.3
Python 3.10.11
Intellij 2022.3.2
Ubuntu 20.04 LTS
Debezium 1.5.4
Django 4.2.7
```

## Deploy Command
```shell
sudo apt-get update
sudo apt-get upgrade
sudo timedatectl set-timezone Asia/Seoul

sudo apt install ufw
sudo ufw status verbose
sudo ufw enable
sudo ufw default deny incoming 
sudo ufw default allow outgoing
sudo ufw allow ssh
sudo ufw allow http 
sudo ufw allow https
sudo ufw allow 8080
sudo ufw allow 443

sudo apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
sudo usermod -aG docker ubuntu
sudo service docker restart

sudo apt install jq
DCVERSION=$(curl --silent https://api.github.com/repos/docker/compose/releases/latest | jq .name -r)
DCDESTINATION=/usr/bin/docker-compose
sudo curl -L https://github.com/docker/compose/releases/download/${DCVERSION}/docker-compose-$(uname -s)-$(uname -m) -o $DCDESTINATION
sudo chmod 755 $DCDESTINATION
sudo docker-compose -v

sudo docker network create deploy

df -h 
sudo fallocate -l 8G /swapfile 
sudo chmod 600 /swapfile 
sudo mkswap /swapfile 
sudo swapon /swapfile 
free -h

sudo docker run -d \
  --env JAVA_OPTS=-Xmx2g \
  -v /etc/localtime:/etc/localtime:ro \
  -e TZ=Asia/Seoul \
  -p 8080:8080 \
  -v /jenkins:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /usr/bin/docker-compose:/usr/bin/docker-compose \
  --name jenkins \
  -u root \
  jenkins/jenkins:latest

sudo docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword

apt-get update
apt-get -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg> /tmp/dkey; apt-key add /tmp/dkey
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") $(lsb_release -cs) stable"
apt-get update && apt-get -y install docker-ce
```

## Kafka
### Debezium Download
```text
wget -d https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/1.5.4.Final/debezium-connector-mysql-1.5.4.Final-plugin.tar.gz
```

### Topic List
```text
__consumer_offsets
chat.send
connect-configs
connect-offsets
connect-status
fortune-cookie.create
fortune-cookie.use
help-history-command-mysql
help-history-command-mysql.help_history.certificate
help-history-command-mysql.help_history.help_history
help-match.push
help.history.create
member-command-mysql
member-command-mysql.member.admin
member-command-mysql.member.banned_member
member-command-mysql.member.member
member-command-mysql.member.member_device
member-command-mysql.member.member_report
schema-changes.help-history-command-mysql
schema-changes.member-command-mysql
```

## Dockerfile

### General 
```docker
FROM gradle:8.2.1-jdk11 as build
WORKDIR /app

COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./src ./src

RUN gradle build -x test --parallel

FROM azul/zulu-openjdk:11
WORKDIR /app

COPY --from=build /app/build/libs/*.jar ./app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app/app.jar"]
```

### with Chrome Driver(ocr)
```docker
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
```
## ENV(docker-compose.yml)

### api-gateway
```yaml
version: ‘3.7’

services:
  app:
    container_name: api-gateway
    image: api-gateway
    ports:
      - 443:8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: API-GATEWAY-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      JWT_EXPIRE_MIN: 1000000
      JWT_SALT: ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509
      KEY_ALIAS: a509
      KEY_STORE_PASSWORD: ssafyA509
      KEY_STORE: classpath:cert_and_key.p12
      KEY_STORE_TYPE: PKCS12

networks:
  deploy:
    external: true
```

### discovery
```yaml
version: ‘3.7’

services:
  app:
    container_name: discovery
    image: discovery
    ports:
      - 8761:8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul

networks:
  deploy:
    external: true
```

### ocr
```yaml
version: ‘3.7’

services:
  app:
    container_name: ocr
    image: ocr
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: OCR-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      KAFKA_BOOTSTRAP_SERVER_URL: sosohappy.co.kr:9092
      OCR_SECRET_KEY: R3ZVZVFlbnhydnZIY1FGSWhVem1TUVdWb3hES1JiQ1o=
      OCR_API_URL: https://tj5rgu5tam.apigw.ntruss.com/custom/v1/26027/abfe392b0c38d511145d7551a2ddbe1f73086f67cc593118eeb68a86a4cc35e0/general

networks:
  deploy:
    external: true
```

### chat

```yaml
version: ‘3.7’

services:
  app:
    container_name: chat
    image: chat
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: CHAT-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      REDIS_HOST: sosohappy.co.kr
      REDIS_PORT: 8902
      REDIS_PASSWORD: ssafyA509
      DB_ADDRESS: sosohappy.co.kr:3310
      DB_NAME: chat
      DB_USER: a509
      DB_PASSWORD: ssafyA509
      CHAT_SERVER_ID_REPOSITORY_PREFIX: "chatServerId:"
      MEMBER_ID_REPOSITORY_KEY_PREFIX: "chatSessionId:"

networks:
  deploy:
    external: true
```

### member-query
```yaml
version: ‘3.7’

services:
  app:
    container_name: member-query
    image: member-query
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=MEMBER-QUERY-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - REDIS_HOST=sosohappy.co.kr
      - REDIS_PORT=6379
      - REDIS_PASSWORD=ssafyA509

networks:
  deploy:
    external: true  
```
### member-sync
```yaml
version: ‘3.7’

services:
  app:
    container_name: member-sync
    image: member-sync
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=MEMBER-SYNC-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - REDIS_HOST=sosohappy.co.kr
      - REDIS_PORT=6379
      - REDIS_PASSWORD=ssafyA509
      - KAFKA_BOOTSTRAP_SERVER_URL=sosohappy.co.kr:9092

networks:
  deploy:
    external: true
```

### member-command
```yaml
version: ‘3.7’

services:
  app:
    container_name: member-command
    image: member-command
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: MEMBER-COMMAND-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      DB_ADDRESS: sosohappy.co.kr:3306
      DB_NAME: member
      DB_USER: a509
      DB_PASSWORD: ssafyA509
      KAFKA_URL: sosohappy.co.kr:9092
      PASSWORD_HASH_SALT: ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509
      JWT_SALT: ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509ssafyA509
      JWT_EXPIRE_MIN: 100000000

networks:
  deploy:
    external: true
```
### member-report
```yaml
version: ‘3.7’

services:
  app:
    container_name: member-report
    image: member-report
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: MEMBER-REPORT-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      DB_ADDRESS: sosohappy.co.kr:3306
      DB_NAME: member
      DB_USER: a509
      DB_PASSWORD: ssafyA509

networks:
  deploy:
    external: true
```

### help-history-query
```yaml
version: ‘3.7’

services:
  app:
    container_name: help-history-query
    image: help-history-query
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=HELP-HISTORY-QUERY-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - REDIS_HOST=sosohappy.co.kr
      - REDIS_PORT=6380
      - REDIS_PASS=ssafyA509

networks:
  deploy:
    external: true
```

### help-history-sync
```yaml
version: ‘3.7’

services:
  app:
    container_name: help-history-sync
    image: help-history-sync
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=HELP-HISTORY-SYNC-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - REDIS_HOST=sosohappy.co.kr
      - REDIS_PORT=6380
      - REDIS_PASSWORD=ssafyA509
      - KAFKA_BOOTSTRAP_SERVER_URL=sosohappy.co.kr:9092

networks:
  deploy:
    external: true
```

### help-history-command
```yaml
version: ‘3.7’

services:
  app:
    container_name: help-history-command
    image: help-history-command
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: HELP-HISTORY-COMMAND-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      DB_ADDRESS: sosohappy.co.kr:3308
      DB_NAME: help_history
      DB_USER: a509
      DB_PASSWORD: ssafyA509
      KAFKA_URL: sosohappy.co.kr:9092

networks:
  deploy:
    external: true
```

### front-web
```yaml
version: ‘3.7’

services:
  app:
    container_name: front-web
    image: front-web
    ports:
      - 8888:5173
    networks:
      - deploy
    environment:
      NODE_ENV: production

networks:
  deploy:
    external: true
```

### help-match
```yaml
version: ‘3.7’

services:
  app:
    container_name: help-match
    image: help-match
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: HELP-MATCH-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      KAFKA_URL: sosohappy.co.kr:9092
      REDIS_HOST: sosohappy.co.kr
      REDIS_PORT: 8901
      REDIS_PASSWORD: ssafyA509

networks:
  deploy:
    external: true
```

### help-match-observer
```yaml
version: ‘3.7’

services:
  app:
    container_name: help-match-observer
    image: help-match-observer
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: HELP-MATCH-OBSERVER-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      REDIS_HOST: sosohappy.co.kr
      REDIS_PORT: 8901
      REDIS_PASSWORD: ssafyA509

networks:
  deploy:
    external: true
```

### monster
```yaml
version: ‘3.7’

services:
  app:
    container_name: monster
    image: monster
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=MONSTER-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - DB_ADDRESS=sosohappy.co.kr:3307
      - DB_NAME=monster
      - DB_USER=a509
      - DB_PASSWORD=ssafyA509

networks:
  deploy:
    external: true
```

### notification
```yaml
version: ‘3.7’

services:
  app:
    container_name: notification
    image: notification
    ports:
      - 8080
    networks:
      - deploy
    environment:
      - TZ=Asia/Seoul
      - DOMAIN_NAME=NOTIFICATION-SERVICE
      - EUREKA_URL=http://sosohappy.co.kr:8761/eureka
      - DB_ADDRESS=sosohappy.co.kr:3306
      - DB_NAME=member
      - DB_USER=a509
      - DB_PASSWORD=ssafyA509

networks:
  deploy:
    external: true
```

### category
```yaml
version: ‘3.7’

services:
  app:
    container_name: category
    image: category
    ports:
      - 8080
    networks:
      - deploy
    environment:
      TZ: Asia/Seoul
      DOMAIN_NAME: CATEGORY-SERVICE
      EUREKA_URL: http://sosohappy.co.kr:8761/eureka
      DB_ADDRESS: sosohappy.co.kr:3309
      DB_NAME: category
      DB_USER: a509
      DB_PASSWORD: ssafyA509

networks:
  deploy:
    external: true
```

### category-recommend
```text
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql', 
        'NAME': 'category', 
        'USER': 'a509',                      
        'PASSWORD': 'ssafyA509',               
        'HOST': 'sosohappy.co.kr',            
        'PORT': '3309', 
    }
}
```


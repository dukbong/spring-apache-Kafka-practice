# spring-apache-Kafka-practice

### docker-compose.yaml
```yaml
version: '3'
services:
  zookeeper:
    image: zookeeper:3.6.3
    ports:
      - "2181:2181"
    environment:
        ZOOKEEPER_CLIENT_PORT: 2181
  kafka:
    image: wurstmeister/kafka:2.13-2.7.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_HOST://kafka:9093
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:9093
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock

  mysql:
    image: mysql:latest
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: rootpwd
      MYSQL_DATABASE: maindata
      MYSQL_USER: user
      MYSQL_PASSWORD: pwd
    volumes:
      - mysql-data:/var/lib/mysql
```

### Kafka topic 관련 명령어 (CLI)
```
// docker-compose 기준
# docker-compose exec kafka bash

// 새로운 토픽 [test-topic] 추가 및 파티션 개수 설정
# kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092

// 기존 test-topic 토픽에 파티션 2개 설정
# kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic test-topic --partitions 2

// test-topic 설정 확인
# kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic test-topic

// topic 삭제 [my-topic-1]
# kafka-topics.sh --delete --topic my-topic-1 --bootstrap-server localhost:9092
```

## Kafka Example

App show how to send to topic message and how to read message from same topic.

### How to run Kafka

Dowload Kafka docker image:

```terminal
docker pull wurstmeister/kafka
```

Stop and delete existing container (if need):

```terminal
docker-compose stop
docker rm -v health_kafka_1
docker rm -v health_zookeeper_1
```

Start container using Docker Compose:

```terminal
docker-compose up -d
```

### How to run java app

Build app

```terminal
gradlew build
```

and start app

```terminal
java -jar demo-0.0.1-SNAPSHOT.jar
```

### How to check if it works

In the console, you will see the messages that were sent and received

### Links

About Kafka and Spring Boot: [1](https://habr.com/ru/post/505720/), [2](https://habr.com/ru/post/354486/), [3](https://reflectoring.io/spring-boot-kafka/)

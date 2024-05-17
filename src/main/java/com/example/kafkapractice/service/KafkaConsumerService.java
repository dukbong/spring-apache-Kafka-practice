package com.example.kafkapractice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

	// Producer에서 보낼때 직렬화로 보내기 때문에 String, byte[]로 전송된다.
//	@KafkaListener(topics = "my-topic-1", groupId = "my-group-1")
//	public void listenA(String message) {
//		log.info("리스너 A 호출");
//		log.info("======group-1======");
//		log.info("Received message = {}", message);
//		log.info("=============-=====");
//	}
	
	@KafkaListener(topicPartitions = @TopicPartition(topic = "test-topic", partitions = {"0"}), groupId = "my-group-1")
	public void listenA(String message) {
		log.info("my-topic-1 : partition 0");
		log.info("received message = {}", message);
	}
	
	@KafkaListener(topicPartitions = @TopicPartition(topic = "test-topic", partitions = {"1"}), groupId = "my-group-3")
	public void listenB(String message) {
		log.info("my-topic-1 : partition 1");
		log.info("received message = {}", message);
	}
	
//	@KafkaListener(topics = "my-topic-2", groupId = "my-group-2")
//	public void listenB(String message) {
//		log.info("======group-2======");
//		log.info("Received message = {}", message);
//		log.info("==================");
//	}
}

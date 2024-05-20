package com.example.kafkapractice.controller;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkapractice.dto.MyObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	private final RetryTemplate retryTemplate;

//	@PostMapping("/senda")
//	public void sendMessageA(@RequestBody MyObject obj) {
//		retryTemplate.execute(context -> {
//			try {
//				kafkaTemplate.send("test-topic","key1", obj);
//				return null;
//			} catch(Exception e) {
//				log.error("재시도 중");
//				throw new RuntimeException("!!");
//			}
//		}, context -> {
//			log.error("재시도 모두 실패 my-topic-1");
//			return null;
//		});
//	}
	
	@PostMapping("/senda")
	public void sendMessageA(@RequestBody MyObject obj) {
		try {
			// kafka는 브로커에게 비동기적으로 전달하는데 이를 동기적으로 바꿔서 전달한 메시지가 잘 저장되었는지 확인할 수 있다.
			SendResult<String, Object> ackResult = kafkaTemplate.send("test-topic","key1", obj).get();
			RecordMetadata metadata = ackResult.getRecordMetadata();
			log.info("Producer Ack = 1 : 성공");
			log.info("send Topic = {}", metadata.topic());
			log.info("send Pratition = {}", metadata.partition());
			log.info("send Offset = {}", metadata.offset());
		} catch (ExecutionException | InterruptedException e) {
		    // 메시지 전송 실패 시 처리
		    log.error("Ack 실패: " + e.getMessage());
		}
		
	}
	
	@PostMapping("/senda2")
	public void sendMessageA2(@RequestBody MyObject obj) {
		retryTemplate.execute(context -> {
			try {
				kafkaTemplate.send("test-topic","key2", obj);
				return null;
			} catch(Exception e) {
				log.error("재시도 중");
				throw new RuntimeException("!!");
			}
		}, context -> {
			log.error("재시도 모두 실패 my-topic-1");
			return null;
		});
	}

	@PostMapping("/sendb")
	public void sendMessageB(@RequestParam("message") String message) {
		retryTemplate.execute(context -> {
			int random = (int) ((Math.random() * 10) + 1);
			if (random > 5) {
				kafkaTemplate.send("my-topic-2", message);
				return null;
			} else {
				log.error("B - 재시도 중");
				throw new RuntimeException("!!");
			}
		}, context -> {
			log.error("재시도 모두 실패 my-topic-2");
			return null;
		});
	}

}

package com.hwiseo.consumer;

import com.hwiseo.consumer.common.Topic;
import com.hwiseo.consumer.config.ConsumerWorker;
import com.hwiseo.consumer.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootApplication
public class ConsumerApplication {

	private final static String BOOTSTRAP_SERVER = "my-kafka:9092";
	private final static ArrayList<String> TOPIC_NAME_LIST = new ArrayList<>();
	private final static String GROUP_ID = "account-consumer-group";
	private final static int CONSUMER_COUNT = 3;
	private final static List<ConsumerWorker> workers = new ArrayList<>();

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());

		// 컨슈머 설정
		Properties configs = new Properties();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		// 토픽 추가
		Arrays.stream(Topic.values()).forEach(topic -> TOPIC_NAME_LIST.add(topic.getTopicName()));

		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i=0; i<CONSUMER_COUNT; i++) {
			workers.add(new ConsumerWorker(configs, TOPIC_NAME_LIST, i));
		}
		workers.forEach(executorService::execute);
	}

	static class ShutdownThread extends Thread {
		public void run() {
			log.info("Shutdown hook");
			//workers.forEach(ConsumerWorker::stopAndWakeup);
		}
	}
}

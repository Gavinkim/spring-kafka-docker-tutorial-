package com.gavinkim.demo.consumer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServer;
  @Value("${spring.kafka.consumer.value-deserializer}")
  private String keyDeSerializer;
  @Value("${spring.kafka.consumer.value-deserializer}")
  private String valueDeSerializer;
  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String offsetReset;
  @Value("${spring.kafka.consumer.max-poll-records}")
  private String maxPollRecords;
  @Value("${spring.kafka.consumer.enable-auto-commit}")
  private String enableAutoCommit;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return getStringStringConsumerFactory();
  }

  private ConsumerFactory<String, String> getStringStringConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeSerializer);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeSerializer);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean(name = "kafkaListenerContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String>
  kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}

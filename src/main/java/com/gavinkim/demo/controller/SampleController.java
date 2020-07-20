package com.gavinkim.demo.controller;


import com.gavinkim.demo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @Value("${sample.topic.news}")
  private String topicNews;

  @Value("${sample.topic.music}")
  private String topicMusic;

  @Value("${sample.message-key.news}")
  private String messageKeyNews;

  @Value("${sample.message-key.music}")
  private String messageKeyMusic;

  final String success = "published success";

  private final KafkaProducer kafkaProducer;

  @Autowired
  public SampleController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @GetMapping("/send/news/{msg}")
  String sendNews(@PathVariable String msg) {
    kafkaProducer.sendMessage(topicNews, messageKeyNews, msg);
    return success;
  }

  @GetMapping("/send/music/{msg}")
  String sendMusic(@PathVariable String msg) {
    kafkaProducer.sendMessage(topicMusic, messageKeyMusic, msg);
    return success;
  }

}

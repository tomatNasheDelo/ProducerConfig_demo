package com.example.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class JavaKafkaConsumer {


 public void start(String bootstratServer, String consumerGroup, String topic) {

  Properties properties = new Properties();

  // Populate consumer configurations
  properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstratServer);
  properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
  properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
  properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
  properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

  // Instantiate consumer
  try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {

   TopicPartition topicPartition = new TopicPartition(topic, 0);
   consumer.assign(Arrays.asList(topicPartition));

   while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

    for (ConsumerRecord<String, String> record : records) {
     String key = record.key();
     String value = record.value();
     long offset = record.offset();
     int partition = record.partition();
     

     System.out.println("\n\nConsumer received : ");
     System.out.println("----------------------");
     System.out.println("key : " + key);
     System.out.println("value : " + value);
     System.out.println("offset : " + offset);
     System.out.println("partition : " + partition);

    }
   }

  }

 }
    
}

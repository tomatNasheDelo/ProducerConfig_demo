package com.example.main;

import java.util.concurrent.ExecutionException;

import com.example.consumer.JavaKafkaConsumer;

public class App {


    public static void main(String[] args)  throws InterruptedException, ExecutionException{
        
        // JavaKafkaProducer producer = new JavaKafkaProducer();
          JavaKafkaConsumer consumer = new JavaKafkaConsumer();

         String bootstratServer = "127.0.0.1:9092";

         String consumerGroup = "myFirstConsumerGroup";
         String topic = "prop_prop";

        // Thread t2 = new Thread() {

        //     public void run() {
        //      try {
        //       producer.start(bootstratServer, topic);
        //      } catch (InterruptedException e) {
        //       // TODO Auto-generated catch block
        //       e.printStackTrace();
        //      }
        //     }
        //    };


           Thread t1 = new Thread() {
            public void run() {
             consumer.start(bootstratServer, consumerGroup, topic);
            }
           };

           t1.start();
          // t2.start();
    }
    
}

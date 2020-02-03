package com.kafka.kafkasession;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerCallBack {

    public static void main(String args[]){

        //Set Producer properties
        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        System.out.println("Hey Pooja");

        // Create Kafka Producer
        KafkaProducer<String,String> first_producer = new KafkaProducer<String, String>(properties);

        /* Create Producer Record - to send data to Kafka
        All producers lie inside a producer record
        The producer specifies the topic name as well as the message which is to be delivered to Kafka
        */

        ProducerRecord<String, String> record=new ProducerRecord<String, String>("my_first", "Hi Kafka");

        //send data - The data produced by a producer is asynchronous.

        first_producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger = LoggerFactory.getLogger(ProducerCallBack.class);
                if(e==null){
                    logger.info("Successfully received details: \n" +
                            "Topic: "+ recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset:" + recordMetadata.offset() +"\n" +
                            "Timestamp: "+ recordMetadata.timestamp());
                } else {
                    logger.error("Cant produce message",e);
                }
            }
        });

        /*
        The messages we sent till now are without keys, therefore messages without keys get stored in the random partitions and behave asynchronously.

         */
        first_producer.flush(); //The flush() will force all the data to get produced
       // first_producer.close(); //stops the producer

        //If these functions are not executed, data will never be sent to the Kafka, and the consumer will not be able to read it.

    }
}

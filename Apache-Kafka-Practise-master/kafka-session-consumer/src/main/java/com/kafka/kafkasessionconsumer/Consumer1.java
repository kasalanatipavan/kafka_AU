package com.kafka.kafkasessionconsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer1 {
    public static void main(String args[]) {

        Logger logger = LoggerFactory.getLogger(Consumer1.class.getName());
        //Set Consumer properties
        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "consumerApp";
        String topic = "my_first";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //Due to 'earliest', all the messages from the beginning are displayed.
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        System.out.println("Hey I'm consumer");

        //Create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        /* Subscribe the consumer
        To read the message from a topic, we need to connect the consumer to the specified topic.
        A consumer can be subscribed through various subscribe API's.
        Here, we have used Arrays.asList() because may be the user wants to subscribe either to one or multiple topics.
        Therefore, Arrays.asList() allows to subscribe the consumer to multiple topics.
         */

        consumer.subscribe(Arrays.asList(topic));

        //polling for new data
        /* The poll method returns the data fetched from the current partition's offset.
        The time duration is specified till which it waits for the data, else returns an empty ConsumerRecord
        to the consumer. Also, the logger will fetch the record key, partitions, record offset and its value.
        */

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record: records) {
                logger.info("Key: " + record.key() +" Value: " + record.value() );
                logger.info("Partition: " + record.partition() +" Offset: " + record.offset() );
            }
        }
    }

}

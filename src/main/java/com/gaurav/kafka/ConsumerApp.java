package com.gaurav.kafka;

import java.util.concurrent.ExecutionException;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.consumer.ConsumerCreator;
import com.gaurav.kafka.pojo.CustomObject;
import com.gaurav.kafka.producer.ProducerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class ConsumerApp
{
	public static void main(String[] args) {
		runConsumer();
	}

	static void runConsumer() {
		Consumer<Long, CustomObject> consumer = ConsumerCreator.createConsumer();

		int noMessageToFetch = 0;

		while (true) {
			final ConsumerRecords<Long, CustomObject> consumerRecords = consumer.poll(1000);
			if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				System.out.println("Record Key " + record.key());
				System.out.println("Record value " + (record.value() == null? null : record.value().getName()));
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
}

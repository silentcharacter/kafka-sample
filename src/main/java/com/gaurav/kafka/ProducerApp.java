package com.gaurav.kafka;

import java.util.concurrent.ExecutionException;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.pojo.CustomObject;
import com.gaurav.kafka.producer.ProducerCreator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerApp
{
	public static void main(String[] args) {
		runProducer();
	}

	static void runProducer() {
		org.apache.kafka.clients.producer.Producer<Long, CustomObject> producer = ProducerCreator.createProducer();

		for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
			final ProducerRecord<Long, CustomObject> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME,
					new CustomObject(String.valueOf(index), "This is record " + index));
			try {
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
		}
	}
}

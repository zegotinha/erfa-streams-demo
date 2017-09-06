package ch.generali.copa.dataintegration.kafkastreams.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by fabio on 8/29/17.
 */
public class StreamProducer {

    private static final String CORE_AGENTS_TOPIC = "CORE_AGENTS";
    private static final String CORE_CUSTOMERS_TOPIC = "CORE_CUSTOMERS";
    private static final String CORE_CONTRACTS_TOPIC = "CORE_CONTRACTS";


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        // hardcoding the Kafka server URI for this example
        props.put("bootstrap.servers", "54.93.243.62:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "http://52.59.241.253:8081");
        //TODO enable compression
        // props.put("compression.type", "snappy");
        // Hard coding topic too.

        CustomerProducer.produceExampleCustomer(1, CORE_CUSTOMERS_TOPIC, props);
        AgentProducer.produceExampleAgent(500, CORE_AGENTS_TOPIC, props);
        ContractProducer.produceExampleContract(1000, CORE_CONTRACTS_TOPIC, props);
    }


}

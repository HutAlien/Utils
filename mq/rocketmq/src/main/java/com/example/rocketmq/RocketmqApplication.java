package com.example.rocketmq;

import com.example.rocketmq.consumer.MessageInfoSink;
import com.example.rocketmq.producer.MessageInfoSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;


@SpringBootApplication
@EnableBinding({Source.class, Sink.class, MessageInfoSource.class, MessageInfoSink.class})
public class RocketmqApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

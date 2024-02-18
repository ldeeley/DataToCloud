package com.example.datatocloud.service;


import com.example.datatocloud.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("customer",message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset()+"] on partition : " + result.getRecordMetadata().partition());
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
    public void sendEventsToTopic(CustomerEntity customerEntity){
        CompletableFuture<SendResult<String, Object>> future = template.send("customer-entity", customerEntity);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + customerEntity + "] with offset=[" + result.getRecordMetadata().offset()+"] on partition : " + result.getRecordMetadata().partition());
            } else {
                System.out.println("Unable to send message=[" +
                        customerEntity + "] due to : " + ex.getMessage());
            }
        });
    }

}

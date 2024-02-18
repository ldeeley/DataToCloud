package com.example.datatocloud.config;

import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.entity.CustomerEntity;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class CustomerProcessor implements ItemProcessor<CustomerEntity, CustomerEntity> {

//    Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

    @Override
    public CustomerEntity process(CustomerEntity customerEntity) throws Exception {
//        logger.info("Logging customerEntity : "+customerEntity.getName());
        return customerEntity;
    }
}

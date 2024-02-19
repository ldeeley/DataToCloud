package com.example.datatocloud;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class DataToCloudApplication {

    public static Logger logger= LoggerFactory.getLogger(DataToCloudApplication.class);

    @PostConstruct
    public void init() {
        logger.info("Application started message....");
    }


    public static void main(String[] args) {
        logger.info("Application executed groovy....");
        SpringApplication.run(DataToCloudApplication.class, args);
    }

}

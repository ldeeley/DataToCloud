package com.example.datatocloud;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DataToCloudApplicationTests {

    public static Logger logger = LoggerFactory.getLogger(DataToCloudApplicationTests.class);

    @Test
    void contextLoads() {
        logger.info("Test case executing");
        assertEquals(true,true);
    }

}

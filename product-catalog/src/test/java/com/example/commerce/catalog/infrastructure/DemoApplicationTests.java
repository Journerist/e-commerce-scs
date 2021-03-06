package com.example.commerce.catalog.infrastructure;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.commerce.catalog.core.infrastructure.DemoApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@DataMongoTest
@DirtiesContext
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

}

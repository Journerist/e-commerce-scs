package com.example.commerce.catalog.core.domain.entity.customer;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;

import com.example.commerce.catalog.core.infrastructure.db.SequenceGenerator;

public class CustomerFactoryTest {

    @Test
    public void createNew_newCustomersGetsANewId() throws Exception {
        CustomerFactory customerFactory = new CustomerFactory(new SequenceGeneratorMock(null));
        Customer customer = customerFactory.createNew();
        Assert.assertEquals(1L, customer.getId());
    }
    
    @Test
    public void createNew_isGuest() throws Exception {
        CustomerFactory customerFactory = new CustomerFactory(new SequenceGeneratorMock(null));
        Customer customer = customerFactory.createNew();
        Assert.assertEquals(true, customer.isGuest());
    }
 
    private static class SequenceGeneratorMock extends SequenceGenerator {

        public SequenceGeneratorMock(MongoOperations mongo) {
            super(mongo);
        }

        @Override
        public long getNextSequence(String seqName) {
            return 1;
        }
    }
    
}

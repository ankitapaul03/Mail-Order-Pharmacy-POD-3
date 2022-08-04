package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class SuccessResponseTest
{
    @BeforeEach
    void setUp() throws Exception {
    }
    
    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void test() {
        final SuccessResponse successResponseOne = new SuccessResponse();
        successResponseOne.setResponseMessage("Success");
        Assertions.assertEquals((Object)"Success", (Object)successResponseOne.getResponseMessage());
    }
    
    @Test
    void testAllArgs() {
        final SuccessResponse successResponseOne = new SuccessResponse("Failure");
        Assertions.assertEquals((Object)"Failure", (Object)successResponseOne.getResponseMessage());
    }
}
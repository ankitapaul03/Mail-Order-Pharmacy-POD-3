package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import com.mailorderpharmacy.drugservice.entity.ExceptionResponse;
import java.time.LocalDateTime;

class ExceptionResponseTest
{
    LocalDateTime date;
    ExceptionResponse response1;
    ExceptionResponse response2;
    
    ExceptionResponseTest() {
        this.date = LocalDateTime.now();
        this.response1 = new ExceptionResponse();
        this.response2 = new ExceptionResponse("Success", this.date, HttpStatus.OK);
    }
    
    @Test
    void testMessage() {
        this.response1.setMessge("Success");
        Assertions.assertEquals((Object)"Success", (Object)this.response1.getMessge());
    }
    
    @Test
    void testDate() {
        this.response1.setTimestamp(this.date);
        Assertions.assertEquals((Object)this.date, (Object)this.response1.getTimestamp());
    }
    
    @Test
    void testHttpstatus() {
        this.response1.setStatus(HttpStatus.OK);
        Assertions.assertEquals((Object)HttpStatus.OK, (Object)this.response1.getStatus());
    }
}
package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mailorderpharmacy.drugservice.entity.AuthResponse;

class AuthResponseTest
{
    AuthResponse auth;
    AuthResponse auth2;
    
    AuthResponseTest() {
        this.auth = new AuthResponse();
        this.auth2 = new AuthResponse("Uid", "Name", true);
    }
    
    @Test
    void testUid() {
        this.auth.setUid("Uid");
        Assertions.assertEquals((Object)"Uid", (Object)this.auth.getUid());
    }
    
    @Test
    void testName() {
        this.auth.setName("Name");
        Assertions.assertEquals((Object)"Name", (Object)this.auth.getName());
    }
    
    @Test
    void testIsValid() {
        this.auth.setValid(true);
        Assertions.assertEquals((Object)true, (Object)this.auth.isValid());
    }
    
    @Test
    void testToString() {
        final String str = this.auth2.toString();
        Assertions.assertEquals((Object)this.auth2.toString(), (Object)str);
    }
}
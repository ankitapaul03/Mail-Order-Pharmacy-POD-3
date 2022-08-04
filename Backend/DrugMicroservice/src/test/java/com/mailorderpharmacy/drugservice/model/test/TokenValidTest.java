package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mailorderpharmacy.drugservice.entity.TokenValid;

class TokenValidTest
{
    TokenValid token1;
    TokenValid token2;
    
    TokenValidTest() {
        this.token1 = new TokenValid();
        this.token2 = new TokenValid("Uid", "Name", true);
    }
    
    @Test
    void testUid() {
        this.token1.setUid("Uid");
        Assertions.assertEquals((Object)"Uid", (Object)this.token1.getUid());
    }
    
    @Test
    void testName() {
        this.token1.setName("Name");
        Assertions.assertEquals((Object)"Name", (Object)this.token1.getName());
    }
    
    @Test
    void testIsValid() {
        this.token1.setValid(true);
        Assertions.assertEquals((Object)true, (Object)this.token1.isValid());
    }
    
    @Test
    void testToString() {
        final String str = this.token1.toString();
        Assertions.assertEquals((Object)this.token1.toString(), (Object)str);
    }
}
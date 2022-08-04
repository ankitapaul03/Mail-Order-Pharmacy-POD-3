package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mailorderpharmacy.drugservice.entity.Stock;
import java.util.Date;

class StockTest
{
    Date date;
    Stock stock1;
    Stock stock2;
    
    StockTest() {
        this.date = new Date();
        this.stock1 = new Stock();
        this.stock2 = new Stock("D1", "Drug1", this.date, 25);
    }
    
    @Test
    void testDrugId() {
        this.stock1.setDrugId("D1");
        Assertions.assertEquals((Object)"D1", (Object)this.stock1.getDrugId());
    }
    
    @Test
    void testDrugName() {
        this.stock1.setDrugName("Drug1");
        Assertions.assertEquals((Object)"Drug1", (Object)this.stock1.getDrugName());
    }
    
    @Test
    void testDate() {
        this.stock1.setExpiryDate(this.date);
        Assertions.assertEquals((Object)this.date, (Object)this.stock1.getExpiryDate());
    }
    
    @Test
    void testStock() {
        this.stock1.setStock(25);
        Assertions.assertEquals(25, this.stock1.getStock());
    }
}
package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;

class DrugLocationDetailsTest
{
    DrugDetails drugDetails;
    DrugLocationDetails details1;
    DrugLocationDetails details2;
    
    DrugLocationDetailsTest() {
        this.drugDetails = new DrugDetails();
        this.details1 = new DrugLocationDetails();
        this.details2 = new DrugLocationDetails("ABC", "Chennai", 25, this.drugDetails);
    }
    
    @Test
    void testSerialId() {
        this.details1.setSerialId("ABC");
        Assertions.assertEquals((Object)"ABC", (Object)this.details2.getSerialId());
    }
    
    @Test
    void testLocation() {
        this.details1.setLocation("Chennai");
        Assertions.assertEquals((Object)"Chennai", (Object)this.details2.getLocation());
    }
    
    @Test
    void testQuantity() {
        this.details1.setQuantity(25);
        Assertions.assertEquals(25, this.details2.getQuantity());
    }
    
    @Test
    void testDrugList() {
        this.details1.setDrugDetails(this.drugDetails);
        Assertions.assertEquals((Object)this.drugDetails, (Object)this.details2.getDrugDetails());
    }
}
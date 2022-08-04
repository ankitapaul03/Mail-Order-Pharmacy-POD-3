package com.mailorderpharmacy.drugservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import java.util.List;
import java.util.Date;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;

class DrugDetailsTest
{
    DrugDetails drugDetails;
    Date date;
    List<DrugLocationDetails> list;
    DrugDetails drugDetails2;
    
    DrugDetailsTest() {
        this.drugDetails = new DrugDetails();
        this.date = new Date();
        this.list = new ArrayList<DrugLocationDetails>();
        this.drugDetails2 = new DrugDetails("D1", "Drug1", "Manufacturer1", this.date, this.date, new ArrayList<DrugLocationDetails>());
    }
    
    @Test
    void testDrugId() {
        this.drugDetails.setDrugId("D1");
        Assertions.assertEquals((Object)"D1", (Object)this.drugDetails.getDrugId());
    }
    
    @Test
    void testDrugName() {
        this.drugDetails.setDrugName("Drug1");
        Assertions.assertEquals((Object)"Drug1", (Object)this.drugDetails.getDrugName());
    }
    
    @Test
    void testDrugManufacturer() {
        this.drugDetails.setManufacturer("Manufacturer1");
        Assertions.assertEquals((Object)"Manufacturer1", (Object)this.drugDetails.getManufacturer());
    }
    
    @Test
    void testDrugManufactureDate() {
        this.drugDetails.setManufactureDate(this.date);
        Assertions.assertEquals((Object)this.date, (Object)this.drugDetails.getManufactureDate());
    }
    
    @Test
    void testDrugExpireDate() {
        this.drugDetails.setExpiryDate(this.date);
        Assertions.assertEquals((Object)this.date, (Object)this.drugDetails2.getExpiryDate());
    }
    
    @Test
    void testDrugLocationQuantities() {
        this.drugDetails.setDruglocationQuantities(this.list);
        Assertions.assertEquals((Object)this.list, (Object)this.drugDetails.getDruglocationQuantities());
    }
}
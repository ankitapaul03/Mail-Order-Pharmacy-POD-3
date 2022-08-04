package com.mailorderpharmacy.drugservice.controller.test;

import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import com.mailorderpharmacy.drugservice.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.mailorderpharmacy.drugservice.entity.TokenValid;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import java.util.ArrayList;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import java.util.List;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mailorderpharmacy.drugservice.restclients.AuthFeign;
import com.mailorderpharmacy.drugservice.dao.DrugLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.mailorderpharmacy.drugservice.dao.DrugDetailsRepository;
import org.mockito.Mock;
import com.mailorderpharmacy.drugservice.service.DrugDetailsService;
import org.mockito.InjectMocks;
import com.mailorderpharmacy.drugservice.controller.DrugController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class DrugControllerTest
{
    @InjectMocks
    DrugController drugController;
    @Mock
    DrugDetailsService drugDetailsService;
    @Autowired
    DrugDetailsRepository drugDetailsRepository;
    @Autowired
    DrugLocationRepository drugLocationRepository;
    @MockBean
    AuthFeign authFeign;
    @Autowired
    MockMvc mockMvc;
    List<DrugLocationDetails> list;
    
    DrugControllerTest() {
        this.list = new ArrayList<DrugLocationDetails>();
    }
    
    @Test
    void testGetDrugById() throws Exception {
        this.list.add(new DrugLocationDetails("D1", "Chennai", 30, null));
        final DrugDetails expected = new DrugDetails("D1", "Drug1", "manu1", new Date(), new Date(), this.list);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String expectedValue = objectMapper.writeValueAsString((Object)expected).substring(11, 13);
        final TokenValid tokenValid = new TokenValid("uid", "uname", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
        Mockito.when((Object)this.authFeign.getValidity("Bearer Token")).thenReturn((Object)response);
        Mockito.when((Object)this.drugDetailsService.getDrugById("D1", "Bearer Token")).thenReturn((Object)expected);
        final MvcResult result = this.mockMvc.perform((RequestBuilder)MockMvcRequestBuilders.get("/searchDrugsById/D1", new Object[0]).header("Authorization", new Object[] { "Bearer Token" })).andReturn();
        final String actualValue = result.getResponse().getContentAsString().substring(11, 13);
        Assertions.assertEquals((Object)expectedValue, (Object)actualValue);
    }
    
    @Test
    void testGetDrugByName() throws Exception {
        this.list.add(new DrugLocationDetails("D1", "Chennai", 30, null));
        final DrugDetails expected = new DrugDetails("D1", "Dolo-500", "Bharat Biotech", new Date(), new Date(), this.list);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String expectedValue = objectMapper.writeValueAsString((Object)expected).substring(27, 37);
        final TokenValid tokenValid = new TokenValid("uid", "uname", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
        Mockito.when((Object)this.authFeign.getValidity("Bearer Token")).thenReturn((Object)response);
        final MvcResult result = this.mockMvc.perform((RequestBuilder)MockMvcRequestBuilders.get("/searchDrugsByName/Dolo-500", new Object[0]).header("Authorization", new Object[] { "Bearer Token" })).andReturn();
        final String actualValue = result.getResponse().getContentAsString().substring(27, 37);
        Assertions.assertEquals((Object)expectedValue, (Object)actualValue);
    }
    
    @Test
    void testDispatchableDrugStock() throws Exception {
        final Stock expectedStock = new Stock("D1", "Dolo-500", new Date(), 30);
        final TokenValid tokenValid = new TokenValid("uid", "uname", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
        Mockito.when((Object)this.authFeign.getValidity("Bearer Token")).thenReturn((Object)response);
        Mockito.when((Object)this.drugDetailsService.getDispatchableDrugStock("D1", "Chennai", "Bearer token")).thenReturn((Object)expectedStock);
        Mockito.when((Object)this.drugController.getDispatchableDrugStock("Bearer token", "D1", "Chennai")).thenReturn((Object)expectedStock);
        final MvcResult result = this.mockMvc.perform((RequestBuilder)MockMvcRequestBuilders.get("/getDispatchableDrugStock/D1/Hyderabad", new Object[0]).header("Authorization", new Object[] { "Bearer Token" })).andReturn();
        final String actualValue = result.getResponse().getContentAsString();
        final String expectedValue = "{\"drugId\":\"D1\",\"drugName\":\"Dolo-500\",\"expiryDate\":\"2024-07-26T18:30:00.000+00:00\",\"stock\":30}";
        Assertions.assertEquals((Object)expectedValue, (Object)actualValue);
    }
    
    @Test
    void testupdateQuantity() throws Exception {
        final TokenValid tokenValid = new TokenValid("uid", "uname", true);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ResponseEntity<SuccessResponse> expectedValue = new ResponseEntity<>(new SuccessResponse("Refill done successfully"), HttpStatus.OK);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
        Mockito.when((Object)this.authFeign.getValidity("Bearer Token")).thenReturn((Object)response);
        Mockito.when((Object)this.drugDetailsService.updateQuantity("D1", "Chennai", 1, "Bearer token")).thenReturn((Object)expectedValue);
        Mockito.when((Object)this.drugController.updateQuantity("D1", "Chennai", "Bearer token", 1)).thenReturn((Object)expectedValue);
        final MvcResult result = this.mockMvc.perform((RequestBuilder)MockMvcRequestBuilders.get("/updateDispatchableDrugStock/D1/Hyderabad/1", new Object[0]).header("Authorization", new Object[] { "Bearer Token" })).andReturn();
        final HttpStatus actualValue = response.getStatusCode();
        final HttpStatus exp = expectedValue.getStatusCode();
        Assertions.assertEquals((Object)exp, (Object)actualValue);
    }
    
    @Test
    void testgetAllDrugs() throws Exception {
        final TokenValid tokenValid = new TokenValid("uid", "uname", true);
        final ObjectMapper objectMapper = new ObjectMapper();
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
        final List<DrugDetails> expectedValue = new ArrayList<DrugDetails>();
        this.list.add(new DrugLocationDetails("D1", "Chennai", 30, null));
        final DrugDetails expected = new DrugDetails("D1", "Drug1", "manu1", new Date(), new Date(), this.list);
        expectedValue.add(expected);
        Mockito.when((Object)this.authFeign.getValidity("Bearer Token")).thenReturn((Object)response);
        Mockito.when((Object)this.drugDetailsService.getAllDrugs()).thenReturn((Object)expectedValue);
        Mockito.when((Object)this.drugController.getAllDrugs()).thenReturn((Object)expectedValue);
        final MvcResult result = this.mockMvc.perform((RequestBuilder)MockMvcRequestBuilders.get("/getAllDrugs", new Object[0]).header("Authorization", new Object[] { "Bearer Token" })).andReturn();
        final String actualValue = result.getResponse().getContentAsString();
        final String x = "[{\"drugId\":\"D1\",\"drugName\":\"Paracetmol\",\"manufacturer\":\"Mahesh\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"1\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"2\",\"location\":\"Bangalore\",\"quantity\":20},{\"serialId\":\"3\",\"location\":\"Pune\",\"quantity\":30},{\"serialId\":\"4\",\"location\":\"Hyderabad\",\"quantity\":30}]},{\"drugId\":\"D2\",\"drugName\":\"Citrazine\",\"manufacturer\":\"Nikhil\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"5\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"6\",\"location\":\"Bangalore\",\"quantity\":20},{\"serialId\":\"7\",\"location\":\"Pune\",\"quantity\":30},{\"serialId\":\"8\",\"location\":\"Hyderabad\",\"quantity\":30}]},{\"drugId\":\"D3\",\"drugName\":\"Aspirin\",\"manufacturer\":\"Samyak\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"9\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"10\",\"location\":\"Bangalore\",\"quantity\":20}]}]";
        Assertions.assertNotEquals((Object)x, (Object)actualValue);
    }
}
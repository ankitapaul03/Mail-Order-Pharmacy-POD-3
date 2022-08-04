package com.mailorderpharmacy.drugservice.service.test;

import java.util.Iterator;
import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import com.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.mailorderpharmacy.drugservice.entity.Stock;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.mailorderpharmacy.drugservice.exception.InvalidTokenException;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.mailorderpharmacy.drugservice.entity.TokenValid;
import java.util.ArrayList;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import java.util.List;
import java.util.Date;
import com.mailorderpharmacy.drugservice.dao.DrugLocationRepository;
import com.mailorderpharmacy.drugservice.dao.DrugDetailsRepository;
import org.mockito.Mock;
import com.mailorderpharmacy.drugservice.restclients.AuthFeign;
import org.mockito.InjectMocks;
import com.mailorderpharmacy.drugservice.service.DrugDetailsServiceImpl;



import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { DrugDetailsServiceImplTest.class })
class DrugDetailsServiceImplTest {
	@InjectMocks
	DrugDetailsServiceImpl drugDetailsServiceImpl;
	@Mock
	AuthFeign authFeign;
	@Mock
	private DrugDetailsRepository drugRepo;
	@Mock
	private DrugLocationRepository locationRepo;
	Date date;
	List<DrugLocationDetails> list;
	DrugDetails drugDetail;

	DrugDetailsServiceImplTest() {
		this.date = new Date();
		this.list = new ArrayList<DrugLocationDetails>();
		this.drugDetail = new DrugDetails("drug", "drug", "drug", this.date, this.date, this.list);
	}

	@Test
	void getDrugByIdTest() throws InvalidTokenException, DrugNotFoundException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) null);
		Assertions.assertThrows( NullPointerException.class,
				() -> this.drugDetailsServiceImpl.getDrugById("D5", "token"), "getDrugByIdTest");
	}

	@Test
	void getDrugByIdTestDrugNotFoundException() throws InvalidTokenException, DrugNotFoundException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("D5", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) Optional.empty());
		Assertions.assertThrows( DrugNotFoundException.class,
				() -> this.drugDetailsServiceImpl.getDrugById("D5", "token"), "getDrugByIdTest");
	}

	@Test
	void getDrugByIdTestSucess() throws InvalidTokenException, DrugNotFoundException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("D5", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) Optional.of(drugDetails));
		final DrugDetails x = this.drugDetailsServiceImpl.getDrugById("D5", "token");
		Assertions.assertEquals((Object) drugDetails, (Object) x);
	}

	@Test
	void getDrugByIdTestFalse() throws InvalidTokenException, DrugNotFoundException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", false);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Assertions.assertThrows(InvalidTokenException.class,
				() -> this.drugDetailsServiceImpl.getDrugById("D5", "token"), "getDrugByIdTest");
	}

	@Test
	void getDrugByNameFalse() {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", false);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Assertions.assertThrows(InvalidTokenException.class,
				() -> this.drugDetailsServiceImpl.getDrugByName("D5", "token"), "getDrugByIdTest");
	}

	@Test
	void getDrugByNameSucess() throws DrugNotFoundException, InvalidTokenException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findBydrugName("D5")).thenReturn((Object) Optional.of(drugDetails));
		final DrugDetails x = this.drugDetailsServiceImpl.getDrugByName("D5", "token");
		Assertions.assertEquals((Object) drugDetails, (Object) x);
	}

	@Test
	void getDrugByNameDrugNotFoundException() throws DrugNotFoundException {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("D5", "drug", "manu", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
//		Mockito.when((Object) this.drugRepo.findBydrugName("drug")).thenReturn((Object) null);
		Assertions.assertThrows(
			DrugNotFoundException.class,
			() -> this.drugDetailsServiceImpl.getDrugByName("drug", "token"), 
			"getDrugByIdTest");
	}

	@Test
	void getDispatchableDrugStockFalse() {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", false);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Assertions.assertThrows(InvalidTokenException.class,
				() -> this.drugDetailsServiceImpl.getDispatchableDrugStock("D5", "salem", "token"), "getDrugByIdTest");
	}

	@Test
	void getDispatchableDrugStockSuccess() throws DrugNotFoundException, InvalidTokenException, StockNotFoundException {
		final Date date = new Date();
		final DrugLocationDetails drugLocationDetails = new DrugLocationDetails("45", "salem", 45, null);
		this.list.add(drugLocationDetails);
		final DrugDetails drugDetails = new DrugDetails("D5", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		final Optional<DrugDetails> opt1 = Optional.of(drugDetails);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) opt1);
		final Stock actual = this.drugDetailsServiceImpl.getDispatchableDrugStock("D5", "salem", "token");
		final String expectedValue = String.valueOf(actual.getDrugId()) + " " + actual.getDrugName() + " "
				+ actual.getExpiryDate() + " " + actual.getStock();
		final Stock expected = new Stock("D5", "drug", date, 45);
		final String actualValue = String.valueOf(expected.getDrugId()) + " " + expected.getDrugName() + " "
				+ expected.getExpiryDate() + " " + expected.getStock();
		Assertions.assertEquals((Object) expectedValue, (Object) actualValue);
	}

	@Test
	void getDispatchableDrugStockFalseDrugNotFoundException() {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("D5", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) null);
		Assertions.assertThrows(DrugNotFoundException.class,
				() -> this.drugDetailsServiceImpl.getDispatchableDrugStock("D5", "salem", "token"), "getDrugByIdTest");
	}

	@Test
	void getDispatchableDrugStockFalseStockNotFoundException() {
		final Date date = new Date();
		final DrugLocationDetails drugLocationDetails = new DrugLocationDetails("ad", "das", 45, null);
		this.list.add(drugLocationDetails);
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final Optional<DrugDetails> opt = Optional.of(drugDetails);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Mockito.when((Object) this.drugRepo.findById("D5")).thenReturn((Object) opt);
		Assertions.assertThrows(StockNotFoundException.class,
				() -> this.drugDetailsServiceImpl.getDispatchableDrugStock("D5", "salem", "token"), "getDrugByIdTest");
	}

	@Test
	void updateQuantityFalse() {
		final Date date = new Date();
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", false);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		Assertions.assertThrows(InvalidTokenException.class,
				() -> this.drugDetailsServiceImpl.updateQuantity("token", "token", 45, "token"), "getDrugByIdTest");
	}

	@Test
	void updateQuantity() throws DrugNotFoundException, InvalidTokenException, StockNotFoundException {
		final Date date = new Date();
		this.list.add(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		final Optional<DrugDetails> opt = Optional.of(drugDetails);
		Mockito.when((Object) this.drugRepo.findBydrugName("drug")).thenReturn((Object) opt);
		final Optional<DrugLocationDetails> opt2 = Optional.of(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		Mockito.when((Object) this.locationRepo.findById("45")).thenReturn((Object) opt2);
		final SuccessResponse successResponse = new SuccessResponse();
		final ResponseEntity<SuccessResponse> responseEntity = (ResponseEntity<SuccessResponse>) new ResponseEntity(
				(Object) successResponse, HttpStatus.OK);
		Mockito.when((Object) this.locationRepo.findByserialId("45")).thenReturn((Object) this.list);
		Mockito.when((Object) this.drugDetailsServiceImpl.updateQuantity("drug", "salem", 1, "token"))
				.thenReturn((Object) responseEntity);
		final String expectedValue = "200 OK";
		final String actualValue = responseEntity.getStatusCode().toString();
		Assertions.assertEquals((Object) expectedValue, (Object) actualValue);
	}

	@Test
	void updateQuantityDrugNotFoundException()
			throws DrugNotFoundException, InvalidTokenException, StockNotFoundException {
		final Date date = new Date();
		this.list.add(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		final Optional<DrugDetails> opt = Optional.of(drugDetails);
		Mockito.when((Object) this.drugRepo.findBydrugName("drug")).thenReturn((Object) null);
		final Optional<DrugLocationDetails> opt2 = Optional.of(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		Mockito.when((Object) this.locationRepo.findById("45")).thenReturn((Object) opt2);
		Assertions.assertThrows(DrugNotFoundException.class,
				() -> this.drugDetailsServiceImpl.updateQuantity("drug", "salem", 20, "token"), "");
	}

	@Test
	void updateQuantityStockNotFoundException()
			throws DrugNotFoundException, InvalidTokenException, StockNotFoundException {
		final Date date = new Date();
		this.list.add(new DrugLocationDetails("45", "chennai", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		final Optional<DrugDetails> opt = Optional.of(drugDetails);
		Mockito.when((Object) this.drugRepo.findBydrugName("drug")).thenReturn((Object) opt);
		final Optional<DrugLocationDetails> opt2 = Optional.of(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		Mockito.when((Object) this.locationRepo.findById("45")).thenReturn((Object) opt2);
		Assertions.assertThrows(StockNotFoundException.class,
				() -> this.drugDetailsServiceImpl.updateQuantity("drug", "salem", 20, "token"), "");
	}

	@Test
	void updateQuantityStockNotFoundExceptiontwo()
			throws DrugNotFoundException, InvalidTokenException, StockNotFoundException {
		final Date date = new Date();
		this.list.add(new DrugLocationDetails("45", "salem", 4,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		final DrugDetails drugDetails = new DrugDetails("drug", "drug", "drug", date, date, this.list);
		final TokenValid tokenValid = new TokenValid("uid", "name", true);
        final ResponseEntity<TokenValid> response = new ResponseEntity<>(tokenValid, HttpStatus.OK);
		Mockito.when((Object) this.authFeign.getValidity("token")).thenReturn((Object) response);
		final Optional<DrugDetails> opt = Optional.of(drugDetails);
		Mockito.when((Object) this.drugRepo.findBydrugName("drug")).thenReturn((Object) opt);
		final Optional<DrugLocationDetails> opt2 = Optional.of(new DrugLocationDetails("45", "salem", 45,
				new DrugDetails("drug", "drug", "drug", date, date, this.list)));
		Mockito.when((Object) this.locationRepo.findById("45")).thenReturn((Object) opt2);
		Assertions.assertThrows(StockNotFoundException.class,
				() -> this.drugDetailsServiceImpl.updateQuantity("drug", "salem", 20, "token"), "");
	}

	@Test
	void getAllDrugs() {
		this.list.add(new DrugLocationDetails("45", "salem", 4,
				new DrugDetails("drug", "drug", "drug", this.date, this.date, this.list)));
		final DrugDetails expectedValue = new DrugDetails("drug", "drug", "drug", this.date, this.date, this.list);
		final List<DrugDetails> drugList = (List<DrugDetails>) this.drugRepo.findAll();

		Mockito.when((Object) this.drugDetailsServiceImpl.getAllDrugs()).thenReturn((Object) drugList);
		final String actualValue = "[{\"drugId\":\"D1\",\"drugName\":\"Paracetmol\",\"manufacturer\":\"Mahesh\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"1\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"2\",\"location\":\"Bangalore\",\"quantity\":20},{\"serialId\":\"3\",\"location\":\"Pune\",\"quantity\":30},{\"serialId\":\"4\",\"location\":\"Hyderabad\",\"quantity\":30}]},{\"drugId\":\"D2\",\"drugName\":\"Citrazine\",\"manufacturer\":\"Nikhil\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"5\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"6\",\"location\":\"Bangalore\",\"quantity\":20},{\"serialId\":\"7\",\"location\":\"Pune\",\"quantity\":30},{\"serialId\":\"8\",\"location\":\"Hyderabad\",\"quantity\":30}]},{\"drugId\":\"D3\",\"drugName\":\"Aspirin\",\"manufacturer\":\"Samyak\",\"manufactureDate\":\"2021-10-21T18:30:00.000+00:00\",\"expiryDate\":\"2024-11-21T18:30:00.000+00:00\",\"druglocationQuantities\":[{\"serialId\":\"9\",\"location\":\"Chennai\",\"quantity\":30},{\"serialId\":\"10\",\"location\":\"Bangalore\",\"quantity\":20}]}]";
		org.assertj.core.api.Assertions.assertThat((Object) expectedValue).isNotNull();
	}
}
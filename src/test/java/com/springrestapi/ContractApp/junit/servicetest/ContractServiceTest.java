package com.springrestapi.ContractApp.junit.servicetest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springrestapi.ContractApp.dao.ContractRepository;
import com.springrestapi.ContractApp.domain.Contract;
import com.springrestapi.ContractApp.service.ContractService;
import com.springrestapi.ContractApp.service.impl.ContractServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest({ ContractServiceImpl.class })
public class ContractServiceTest {

	@Autowired
	ContractService contractService;
	@MockBean
	ContractRepository mockContractRepository;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		List<Contract> nsrchRespList = new ArrayList<>();
		Contract contract = new Contract();
		contract.setContractID(1l);
		contract.setAmountRequest(10000.00);
		contract.setName("Test1");
		contract.setBusinessNumber("34567");
		contract.setContractActivationDate("");
		nsrchRespList.add(contract);
		Contract contract1 = new Contract();
		contract1.setContractID(1l);
		contract1.setAmountRequest(10000.00);
		contract1.setName("Test1");
		contract1.setBusinessNumber("34567");
		contract1.setContractActivationDate("");
		nsrchRespList.add(contract1);

		Mockito.when(mockContractRepository.findStatusApproved(Mockito.anyString())).thenReturn(nsrchRespList);

	}

	@Test
	public void findStatusApprovedTest() {
		List<Contract> contractList = contractService.findStatusApproved();
		assertEquals(2, contractList.size());

	}

	@Test
	public void saveContractTest() {
		Contract contract = new Contract(1L, "Test", "Approved", "1234", "Test", 10000d);
		Mockito.when(mockContractRepository.save(Mockito.any())).thenReturn(contract);
		contract = contractService.saveContract(contract);
		assertEquals("Test", contract.getName());
	}
	
	@Test
	public void deleteContractTest(){
		contractService.deleteContract(1L);
		Mockito.verify(mockContractRepository, Mockito.times(1)).deleteById(1L);
	}
	
	@Test
	public void findOneTest() {
		Contract contract = new Contract(1L, "Test", "Approved", "1234", "Test", 10000d);
		Mockito.when(mockContractRepository.findById(1L)).thenReturn(Optional.of(contract));
		Optional<Contract> contract1 = contractService.findOne(1L);
		if (contract1.isPresent()) {
			assertEquals("Test", contract.getName());
		}
	}
	
	
}

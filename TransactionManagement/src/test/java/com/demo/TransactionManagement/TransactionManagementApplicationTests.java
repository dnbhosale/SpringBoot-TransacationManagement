package com.demo.TransactionManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	  @Autowired
	    FundTransferService fundTransferService;
	  
	    @Autowired
	    BankAccountRepository bankAccountRepository;

	    @Test
	    @Transactional
	    public void testFundTransfer() {
	        BankAccount ba1 = new BankAccount();
	        BankAccount ba2 = new BankAccount();
	        ba1.setId(1);
	        ba1.setBalance(1000);
	        ba1.setAccountNumber("1234567890");
	        ba2.setId(2);
	        ba2.setBalance(1000);
	        ba2.setAccountNumber("0987654321");
	        bankAccountRepository.save(ba1);
	        bankAccountRepository.save(ba2);

	        fundTransferService.fundTransfer(1, 2, 500);

	        BankAccount updatedBa1 = bankAccountRepository.findById(1).orElseThrow();
	        BankAccount updatedBa2 = bankAccountRepository.findById(2).orElseThrow();
	      
	        assertEquals(500, updatedBa1.getBalance());
	        assertEquals(1500, updatedBa2.getBalance());	}

}

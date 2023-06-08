package com.demo.TransactionManagement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundTransferService {
	
	   @Autowired
	    BankAccountRepository bankAccountRepository;

	    @Transactional
	    public void fundTransfer(int fromAccId, int toAccId, double amount) {

	        BankAccount from = bankAccountRepository.findById(fromAccId).orElseThrow();
	        BankAccount to = bankAccountRepository.findById(toAccId).orElseThrow();

	        from.setBalance(from.getBalance() - amount);
	        to.setBalance(to.getBalance() + amount);

	        bankAccountRepository.save(from);
	        bankAccountRepository.save(to);
		
	}

}

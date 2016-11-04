package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.AmountZeroOrNegativeException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {
	
	Account createAccount(int accountNumber,int amount) throws InsufficientInitialBalanceException;
	
	Account depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException,AmountZeroOrNegativeException;
	
	
	Account withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException,AmountZeroOrNegativeException;
	
	List<Account> fundTransfer(int fromAccount,int toAccount , int amount) throws InvalidAccountNumberException,InsufficientBalanceException;
	int showBalance(int accountNumber)throws InvalidAccountNumberException;

}

package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.AmountZeroOrNegativeException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;
	Account account = new Account();

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException {

		if (amount < 500) {
			throw new InsufficientInitialBalanceException();
		}

		account.setAccountNumber(accountNumber);
		account.setAmount(amount);

		if (accountRepository.save(account)) {
			return account;
		}

		return null;
	}

	@Override
	public Account depositAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, AmountZeroOrNegativeException {
		if (amount <= 0) {
			throw new AmountZeroOrNegativeException();
		}
		accountRepository = new AccountRepositoryImpl();
		account = accountRepository.searchAccount(accountNumber);
		if (account != null) {
			int amount2 = account.getAmount();
			amount2 = amount2 + amount;
			account.setAmount(amount2);
			
		} else {
			throw new AmountZeroOrNegativeException();
		}
		return account;
	}

	@Override
	public Account withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException, AmountZeroOrNegativeException {
		if (amount <= 0) {
			throw new AmountZeroOrNegativeException();
		}
		if ((account.getAmount() <= 0) && (amount < account.getAmount())) {
			throw new InsufficientBalanceException();
		}
		accountRepository = new AccountRepositoryImpl();
		account = accountRepository.searchAccount(accountNumber);
		if (account != null) {
			int amount2 = account.getAmount();
			amount2 = amount2 - amount;
			account.setAmount(amount2);
		} else {
			throw new AmountZeroOrNegativeException();
		}
		return account;
	}

	@Override
	public List<Account> fundTransfer(int fromAccount, int toAccount, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int showBalance(int accountNumber) throws InvalidAccountNumberException {
		accountRepository = new AccountRepositoryImpl();
		account = accountRepository.searchAccount(accountNumber);
		if(account==null){
			
			throw new InvalidAccountNumberException();
		}
		return account.getAmount();
	}

}

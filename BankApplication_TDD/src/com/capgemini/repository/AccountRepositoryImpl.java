package com.capgemini.repository;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Account;

public class AccountRepositoryImpl implements AccountRepository {

	List<Account> list = new ArrayList();

	@Override
	public boolean save(Account account) {

		if (account != null) {
			list.add(account);
			return true;
		}
		return false;

	}

	@Override
	public Account searchAccount(int accountNumber) {

		for (Account account : list) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}

		return null;
	}

}

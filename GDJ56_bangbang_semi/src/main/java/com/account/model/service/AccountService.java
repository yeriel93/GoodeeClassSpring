package com.account.model.service;

public class AccountService {
	private static AccountService accountService;
	private AccountService() {}
	public static AccountService getAccountService() {
		if(accountService == null) accountService = new AccountService();
		return accountService;
	}
	
}

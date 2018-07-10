package com.amwater.microservices.demo;

import java.io.Serializable;

public class AccountId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountNumber;

	public AccountId() {}

	public AccountId(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber == null ? "" : accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "AccountId [accountNumber=" + accountNumber + "]";
	}
}

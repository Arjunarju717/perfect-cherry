package com.perfectcherry.dto;

import com.perfectcherry.entity.Interest;
import com.perfectcherry.entity.UserAccount;

public class InterestBean {
	
	private Interest interest;
	private UserAccount userAccount;
	
	public InterestBean(Interest interest, UserAccount userAccount) {
		super();
		this.interest = interest;
		this.userAccount = userAccount;
	}
	
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}

package com.perfectcherry.pcenum;

public enum UserRole {

	A("A") {
		@Override
		public String toString() {
			return "ROLE_ADMIN";
		}
	},
	
	U("U") {  //Name
		@Override
		public String toString() {
			return "ROLE_USER";  //Value
		}
	},

	M("M") {
		@Override
		public String toString() {
			return "ROLE_MANAGER";
		}
	};

	@SuppressWarnings("unused")
	private final String code;

	UserRole(String code) {
		this.code = code;
	}
}

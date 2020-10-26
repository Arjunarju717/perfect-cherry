package com.perfectcherry.pcenum;

public enum InterestEnum {
	
	A("A") {  //Name
		@Override
		public String toString() {
			return "Accepted";  //Value
		}
	},

	D("D") {
		@Override
		public String toString() {
			return "Declined";
		}
	},

	P("P") {
		@Override
		public String toString() {
			return "Pending";
		}
	};

	@SuppressWarnings("unused")
	private final String code;

	InterestEnum(String code) {
		this.code = code;
	}

}

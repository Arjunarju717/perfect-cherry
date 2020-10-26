package com.perfectcherry.pcenum;

public enum UserStatus {
	
	A('A'), O('O');
	
	public char asChar() {
        return code;
    }

	private final char code;

	UserStatus(char code) {
		this.code = code;
	}


}

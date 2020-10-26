package com.perfectcherry.pcenum;

public enum ProfilePhoto {

	Y('Y'), N('N');

	public char asChar() {
		return code;
	}

	private final char code;

	ProfilePhoto(char code) {
		this.code = code;
	}

}

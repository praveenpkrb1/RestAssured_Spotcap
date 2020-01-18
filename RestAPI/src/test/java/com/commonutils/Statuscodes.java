package com.commonutils;

public enum Statuscodes {
	GET(200), POST(201), PUT(200), DELETE(200);

	private int value;

	public int getValue() {
		return value;
	}

	private Statuscodes(int value) {
		this.value = value;
	}
}

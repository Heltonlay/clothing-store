package com.daniel.clothing_store.entities.enums;

public enum PaymentMethod {
	DEBIT_CARD(0), CREDIT_CARD(1), PAYPAL(2), CASH(3);

	private int code;

	private PaymentMethod(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PaymentMethod valueOf(int code) {
		for (PaymentMethod value : PaymentMethod.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid PaymentMethod code");
	}
}

package com.food.swigato.advice;

import lombok.Data;

@Data
public class SwigatoException extends RuntimeException {

	String errorCode;
	String errorMsg;

	public SwigatoException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}

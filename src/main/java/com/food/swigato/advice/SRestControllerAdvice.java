package com.food.swigato.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SRestControllerAdvice {

	@ExceptionHandler(SwigatoException.class)
	ResponseEntity<Map> swigatoException(SwigatoException ex) {
		Map<String, String> errMap = new HashMap<>();

		errMap.put("errCode", ex.getErrorCode());
		errMap.put("errMsg", ex.getErrorMsg());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMap);

	}

}

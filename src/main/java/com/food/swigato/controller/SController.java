package com.food.swigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.swigato.dto.CartDTO;
import com.food.swigato.dto.CartSummary;
import com.food.swigato.entities.BillDetails;
import com.food.swigato.service.AppService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/swigato")
@Slf4j
public class SController {

	@Autowired
	AppService serv;

	// SearchFoodResturant

	// Select Food and Add to cart
	@PostMapping("/addItemToCart")
	ResponseEntity<CartSummary> selectFoodAndAdd(@RequestParam String customerCode, @RequestParam String addressId,
			@RequestParam String productId, @RequestParam String productQuantity) {

		CartSummary response = serv.selectFoodAndAdd(customerCode, addressId, productId, productQuantity);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/cartSummary")
	// @CircuitBreaker(name="cartSummaryCircuitBreaker",
	// fallbackMethod="getCartDetails")
	ResponseEntity<CartDTO> getCartDetails(@RequestParam String customerCode) {
		log.info("Entering into controller class method ");
		CartDTO cartResponse = serv.getCartDetails(customerCode);

		return ResponseEntity.status(HttpStatus.OK).body(cartResponse);

	}

	ResponseEntity<CartDTO> getCartDetails(Throwable throwable) {

		CartDTO cartResponse = CartDTO.builder()
				.billDetails(BillDetails.builder().cartId("customer api is not available ").build()).build();

		return ResponseEntity.status(HttpStatus.OK).body(cartResponse);

	}

	@PostMapping("/checkout")
	ResponseEntity<String> doCheckout(@RequestParam String cartId, @RequestParam Double amount) {

		String response = serv.doCheckout(cartId, amount);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/orderHistory")
	ResponseEntity<List<CartDTO>> viewAllOrders(@RequestParam String customerCode) {

		List<CartDTO> ordersHistory = serv.getAllOrders(customerCode);
		return ResponseEntity.status(HttpStatus.FOUND).body(ordersHistory);

	}

	// Cart Summary

	// Select Delivery Address

	// view order summary and place order

}

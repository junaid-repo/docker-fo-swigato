package com.food.swigato.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.swigato.dto.CartSummary;

public interface CartSummaryRepository extends JpaRepository<CartSummary, String>{

	@Query(value="update cart_summary set cart_status='C' where id=?1", nativeQuery=true)
	void updateCartStatus(String cartId);

}

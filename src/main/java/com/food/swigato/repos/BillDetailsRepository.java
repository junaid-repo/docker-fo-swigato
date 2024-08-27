package com.food.swigato.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.swigato.entities.BillDetails;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

	@Query(value="update bill_details set status='C' where cart_id=?1", nativeQuery=true)
	void updateBillDetails(String cartId);

}

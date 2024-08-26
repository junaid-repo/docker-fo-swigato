package com.food.swigato.api;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiCalls {

	@Autowired
	RestTemplate template;
	
	@Value("${swigato.host}")
	private String host;
	
	@Value("${swigato.resturantPort}")
	private String resturantPort;
	
	@Value("${swigato.customersPort}")
	private String customersPort;

	public Map<String, Object> getProductDetails(String id) {
		// List<GroupMembers> memberList= new ArrayList<>();

		String uri = "http://" + host+":"+resturantPort + "/resturant/get/foodDetails/";
		System.out.println("The uri for getProductDetails "+ uri);
		ResponseEntity<Map<String, Object>> foodDetails = template.exchange(uri + id, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});

		return foodDetails.getBody();

	}

	private HttpHeaders httpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.add("Authorization", "Basic "+getBasicAuthHeader());
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}

	public Map<String, Object> getAddress(Integer id) {
		String uri = "http://" + host+":"+customersPort + "/customer/view/address?id=";
		//log.info("The uri for getAddress "+ uri);
		String finalUri=uri+id;
		System.out.println("The uri for getAddress "+finalUri);
		ResponseEntity<Map<String, Object>> addressDetails = template.exchange(uri + id, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});
		return addressDetails.getBody();
	}
	public Map<String, Object> getCustomerDetails(String userCode) {
		String uri =  "http://" + host+":"+customersPort  + "/customer/view/customerDetails?userCode=";
		String finalUri=uri+userCode;
		System.out.println("The uri for getCustomerDetails "+finalUri);
		ResponseEntity<Map<String, Object>> addressDetails = template.exchange(uri + userCode, HttpMethod.GET,
				new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<Map<String, Object>>() {
				});
		return addressDetails.getBody();
	}

}

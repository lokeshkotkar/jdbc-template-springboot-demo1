package com.lokesh.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

	@Autowired
	private RestTemplate restTemplate;

	public String generateToken() {
		String url = "www.getToken.com";

		HttpHeaders headers = new HttpHeaders();

//		the below parameter is set into body
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//		the below parameters in multivalue map is set into headers
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "grantType");
		map.add("client_id", "clientId");
		map.add("client_secreate", "clentSecreate");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			// response.getStatusCodeValue()
			return null;
		}

	}
	
	private void hitRestEndpoint () {
//		request variable contain any format e.g either it contain json measns key value pair {firstname : lokesh, loastName : kotkar}
		String request = "any format";
		
//		url is endpoint url
		String url = "";
		String generatedToken = generateToken();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+generatedToken);
		HttpEntity entity = new HttpEntity<>(request,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		if(response.getStatusCode()== HttpStatus.CREATED){
			System.out.println("request success");
		}
	}

}

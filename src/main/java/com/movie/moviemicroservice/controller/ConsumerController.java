package com.movie.moviemicroservice.controller;

import com.movie.moviemicroservice.dao.Credentials;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@RequestMapping("call/consumer")
@CrossOrigin("*")
public class ConsumerController
{
	//method invoke form auth service
	@PostMapping(value="/login")
	public ResponseEntity<String> performLogin(@RequestBody Credentials userdto) throws RestClientException, Exception
	{
		String baseUrl = "http://localhost:9099/api/v1/auth/login";
//		String baseUrl = "http://35.92.118.62:8084/auth/v1/login";

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> result;
try
	{
		result = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<String>() {});
	}
	catch(Exception e)
	{
		return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);

	}
	return new ResponseEntity<String>(result.getBody(), HttpStatus.OK);

	}

	//setting headers
	private HttpEntity getHeaders(Credentials userdto) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<Credentials>(userdto, headers);
	}
	

}






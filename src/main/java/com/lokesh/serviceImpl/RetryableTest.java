package com.lokesh.serviceImpl;

import java.util.Date;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryableTest {
	
	@Retryable(value = {Exception.class}, maxAttempts = 3,backoff = @Backoff(delay = 1000))
	public void testRetryable() {
		System.out.println("inside test retryable method   : "+new Date());
		int result = 10/0;
		
	}

}

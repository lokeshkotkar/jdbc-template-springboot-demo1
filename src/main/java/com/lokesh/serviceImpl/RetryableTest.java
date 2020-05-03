package com.lokesh.serviceImpl;

import java.util.Date;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryableTest {
	
	/**
	 * retry 3 times when Exception occures, after retrying 3 times then it will through exception to the calling method 
	 * if we add catch block in this method and capture this exception then it will not going for retry,
	 * it will directly going inside catch block and ate an exception
	 */
	@Retryable(value = {Exception.class}, maxAttempts = 3,backoff = @Backoff(delay = 1000))
	public void testRetryable() {
		System.out.println("inside test retryable method   : "+new Date());
		int result = 10/0;
		
	}

}

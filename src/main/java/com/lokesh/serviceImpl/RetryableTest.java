package com.lokesh.serviceImpl;

import java.util.Date;

import javax.swing.text.NumberFormatter;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryableTest {
	
	/**
	 * retry 3 times when Exception occurs, after retrying 3 times then it will through exception to the calling method 
	 * if we add catch block in this method and capture this exception then it will not going for retry,
	 * it will directly going inside catch block and ate an exception
	 */
	@Retryable(value = {Exception.class}, maxAttempts = 3,backoff = @Backoff(delay = 1000))
	public void testRetryable() {
		System.out.println("inside test retryable method   : "+new Date());
		int result = 10/0;
		
	}

	/**
	 * return type of recover method is same as return type of retry method
	 * the above retry method testRetryable throws arithmetic exception so it will be goes to the recover method and print sysout inside recover method - arithmaticException
	 */
	@Recover
	public void recover(ArithmeticException ex) {
		System.out.println("inside recover method - arithmaticException");
	}
	
	@Recover
	public void recover(NullPointerException ex) {
		System.out.println("inside recover method -NullPointerException ");
	}
	
	@Recover
	public void recover(NumberFormatException ex) {
		System.out.println("inside recover method -NumberFormatException");
	}
	
	
	/**
	 * we can specify any number of exceptions inside retry method
	 */
	@Retryable(value = {NumberFormatException.class,NullPointerException.class}, maxAttempts = 3,backoff = @Backoff(delay = 1000))
	public void testRetryable2() {
		System.out.println("inside test retryable2 method   : "+new Date());
		Integer.parseInt("");
		
	}

}

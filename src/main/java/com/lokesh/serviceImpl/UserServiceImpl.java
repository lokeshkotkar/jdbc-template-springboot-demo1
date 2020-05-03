package com.lokesh.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.Dao.UserDao;

/**
 * @author lokesh
 * this class is for call different dao methods
 *
 */
@Service
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RetryableTest retryableTest;

	public void fetchStudentJson() {
		System.out.println("inside service");
		userDao.executeProc1();
		userDao.executeProc2();
		userDao.executeProc3();
		userDao.executeProc4();
		try {
		retryableTest.testRetryable();
//		if the above method call throws an exception retryableTest.testRetryable() throws an exception then after retrying 3 times it went to the below catch block
		}catch (Exception e) {
			System.out.println("inside service catch block");
		}
	}

}

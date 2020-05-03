package com.lokesh.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.Dao.UserDao;

@Service
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;

	public void fetchStudentJson() {
		System.out.println("inside service");
		// userDao.executeProc1();
//		 userDao.executeProc2();
		userDao.executeProc3();
//		userDao.executeProc4();
	}

}

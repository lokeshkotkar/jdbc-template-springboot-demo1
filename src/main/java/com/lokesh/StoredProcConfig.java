package com.lokesh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lokesh
 * this call is for reading database procedure name from application.properties file
 *
 */
@Component
@ConfigurationProperties(prefix = "stored-proc-name")
public class StoredProcConfig {

	public String getFetchStudent() {
		return fetchStudent;
	}

	public void setFetchStudent(String fetchStudent) {
		this.fetchStudent = fetchStudent;
	}

	public String getFetchStudentWithInputParam() {
		return fetchStudentWithInputParam;
	}

	public void setFetchStudentWithInputParam(String fetchStudentWithInputParam) {
		this.fetchStudentWithInputParam = fetchStudentWithInputParam;
	}

	private String fetchStudent;
	
	private String fetchStudentWithInputParam;
}

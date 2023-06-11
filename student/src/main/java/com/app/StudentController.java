package com.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class StudentController {

	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseData> getStudentDetails(@PathVariable("studentId") long studentId) {
		ResponseData responseData = new ResponseData();

		Student s1 = new Student();

		s1.setStudentId(1L);
		s1.setStudentName("Arun");
		s1.setStudentAdress("bengalore");
		s1.setCollegeId(1L);
		// s1.setCollegeId(1L); here we have to call rest template or web client

		responseData.setStudent(s1);

		Long collegeId = s1.getCollegeId();

		RestTemplate restTemplate = new RestTemplate();

		String endPoint = "http://localhost:9090/college/{collegeId}";

		ResponseEntity<College> data = restTemplate.getForEntity(endPoint, College.class, collegeId);

		// if (data.getStatusCodeValue() == 200) {

		College c1 = data.getBody();
		responseData.setCollege(c1);
		// }
		// return new ResponseEntity<Student>(s1, HttpStatus.OK);
		return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);

	}

}

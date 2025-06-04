package com.userData.userData.client;

import com.userData.userData.Entity.StudentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "student-service",url = "http://localhost:8090")
public interface StudentClient {
    @GetMapping("/student/get/{rollNo}")
    StudentEntity getStudent(@PathVariable("rollNo") String rollNo);

    @GetMapping("/student/findByContact/{contactNo}")
    StudentEntity findByContactNo(@PathVariable("contactNo") String contactNo);



    }


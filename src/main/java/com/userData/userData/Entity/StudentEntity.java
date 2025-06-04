package com.userData.userData.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="STUDENTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rollNo;
    private String firstName;
    private String lastName;
    private String standard;
    private Integer percentage;
    private String contactNo;
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<CourseEntity> courses;

//    public List<CourseEntity> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<CourseEntity> courses) {
//        this.courses = courses;
//    }



}

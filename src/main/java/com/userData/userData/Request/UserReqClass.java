package com.userData.userData.Request;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReqClass {
    private String firstName;
    private String lastName;
    private String userName;
    private String emailId;
    private String password;
    private boolean student;
    private String rollNo;
    private String standard;
    private Integer contactNo;


}
package com.userData.userData.Request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String emailId;
    private String userName;
    private String name;
    private String password;

}

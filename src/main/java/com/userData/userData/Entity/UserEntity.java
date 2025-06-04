package com.userData.userData.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")

public class UserEntity {

    private String emailId;
    @Id
    private String userName;
    private String name;
    private String password;
    private Integer rollNo;



}

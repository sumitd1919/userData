package com.userData.userData.service.implementation;

import com.userData.userData.Entity.StudentEntity;
import com.userData.userData.Entity.UserEntity;
import com.userData.userData.Repository.UserRepo;
import com.userData.userData.Request.User;
import com.userData.userData.Service.Impleamentation.UserServiceImp;
import com.userData.userData.client.StudentClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserRepo userRepo;
    @Mock
    private StudentClient studentClient;

    @Test
    void addUserNameTest() {
        StudentEntity studentEntity = StudentEntity.builder().firstName("test").build();
        when(studentClient.getStudent("123456789")).thenReturn(studentEntity);
        String resp = userService.addUser(User.builder()
                .userName("123456789")
                .name(studentEntity.getFirstName())
                .emailId("abc@gmail.com")
                .password("asdd")
                .build());
        Assertions.assertEquals("user registered", resp);
        Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any(UserEntity.class));


    }

    @Test
    void addUserWithInvalidEmailOrUsername() {
        String resp = userService.addUser(User.builder()
                .userName("123")
                .name("test")
                .emailId("abc@yahoo.com")
                .password("asdd")
                .build());
        Assertions.assertEquals("please check for valid email or your username should be atleast 8 digits long", resp);
        Mockito.verify(userRepo, Mockito.never()).save(Mockito.any());
    }

    @Test
    void passwordAuthenticationSuccess() {
        UserEntity mockUser = UserEntity.builder()
                .userName("john123456")
                .password("secret123")
                .build();

        when(userRepo.findById("john123456")).thenReturn(Optional.of(mockUser));

        String result = userService.fetchUser("john123456", "secret123");

        Assertions.assertEquals("authenticated", result);
    }

    @Test
    void passwordAuthenticationFailed() {
        UserEntity mockUser = UserEntity.builder()
                .userName("john123456")
                .password("correctPassword")
                .build();

        when(userRepo.findById("john123456")).thenReturn(Optional.of(mockUser));

        String result = userService.fetchUser("john123456", "wrongPassword");

        Assertions.assertEquals("Invalid password", result);
    }

    @Test
    void changePasswordSuccess(){
    UserEntity mockUser= UserEntity.builder()
            .userName("123456789")
            .password("oldPassword").build();
    when(userRepo.findByExactUserName("123456789"))
            .thenReturn(Optional.of(mockUser));
    String result=userService.changePassword("123456789","newPassword");

    Assertions.assertEquals("password changed",result);
    Assertions.assertEquals("newPassword",mockUser.getPassword());
    Mockito.verify(userRepo).save(mockUser);




}
}
package com.userData.userData.Service.Impleamentation;

import com.userData.userData.Entity.StudentEntity;
import com.userData.userData.Entity.UserEntity;
import com.userData.userData.Repository.UserRepo;
import com.userData.userData.Request.User;
import com.userData.userData.Request.UserReqClass;
import com.userData.userData.client.StudentClient;
import com.userData.userData.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentClient studentClient;

    @Override
    public String addUser(User user) {
        if (user.getEmailId().endsWith("@gmail.com")){
            StudentEntity studentEntity =  studentClient.getStudent(user.getUserName());
            UserEntity newUser = new UserEntity();
            newUser.setEmailId(user.getEmailId());
            newUser.setUserName(user.getUserName());
            newUser.setName(studentEntity.getFirstName());
            newUser.setPassword(user.getPassword());
            userRepo.save(newUser);
            return"user registered";
        }else
            return "please check for valid email or your username should be atleast 8 digits long";

    }


    @Override
    public String fetchUser(String userName, String enteredPassword) {
        Optional<UserEntity> userEntity = userRepo.findById(userName);
        if (userEntity.isPresent()) {
            String storedPassword = userEntity.get().getPassword();
            if (enteredPassword.equals(storedPassword)) {
                return "authenticated";

            }


            return "Invalid password";

        }
        return "user not registered please sign up.";
    }

    @Override
    public String changePassword(String userName, String newPassword) {
        Optional<UserEntity> userEntity=userRepo.findByExactUserName(userName);
        if (userEntity.isPresent() && userEntity.get().getUserName().equals(userName)){
            userEntity.get().setPassword(newPassword);
            userRepo.save(userEntity.get());
            return "password changed";
        }
        return "no userName found";
    }

    @Override
    public String addUser(UserReqClass userReqClass) {
        if (userReqClass.getEmailId().endsWith("@gmail.com")){
            UserEntity userEntity=new UserEntity();
            userEntity.setName(userReqClass.getFirstName());
            userEntity.setUserName(userReqClass.getUserName());
            userEntity.setEmailId(userReqClass.getEmailId());
            userEntity.setPassword(userReqClass.getPassword());
            if (userReqClass.isStudent()){
               StudentEntity studentEntity= studentClient.getStudent(userReqClass.getRollNo());
               if (studentEntity!=null&&studentEntity.getStandard().equals(userReqClass.getStandard())
               && studentEntity.getContactNo().equals(userReqClass.getContactNo().toString())){

                   userRepo.save(userEntity);
                   return "user registered";
               }
               return "user is not a valid student, kindly check the data entered.";

            }
            userRepo.save(userEntity);
            return "user registered";
        }
        return "kindly check email id";
    }

    @Override
    public String loginUser(String identifier, String password) {
        if (identifier.matches("^\\d{10}$")){
            Optional<StudentEntity> studentEntity = Optional.ofNullable(studentClient.findByContactNo(identifier));
            if (studentEntity.isPresent()) {
                Optional<UserEntity> userEntity1 = userRepo.findByRollNo((studentEntity.get().getRollNo()));
                if (userEntity1.isPresent()) {
                    String storedPassword = userEntity1.get().getPassword();
                    if (password.equals(storedPassword)) {
                        return "User Authenticated";
                    }
                    return "Wrong password";
                }
                return "Invalid details entered by user";

            }
        }
        Optional<UserEntity> userEntity = userRepo.findByExactUserName(identifier);
        if (userEntity.isPresent()) {
            String storedPassword = userEntity.get().getPassword();
            if (password.equals(storedPassword)) {
                return "Authenticated";
            }
            return "wrong password";
        }

        return "No Student found";



    }
}

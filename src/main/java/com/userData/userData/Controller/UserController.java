package com.userData.userData.Controller;

import com.userData.userData.Request.User;
import com.userData.userData.Request.UserReqClass;
import com.userData.userData.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;




    @PostMapping("/user/add")
    public String addUser(@RequestBody User user){
    String resp=userService.addUser(user);
    return resp;

    }

    @GetMapping("/user/get/login")
    public String getUser(@RequestParam String userName, @RequestParam String password){
    return userService.fetchUser(userName,password);

    }
    @PutMapping("/user/changePassword")
    public String changePassword(@RequestParam String userName, @RequestParam String password){
        return userService.changePassword(userName,password);

    }

    @PostMapping("/user/add/new")
    public String addUser(@RequestBody UserReqClass userReqClass){
        String resp=userService.addUser(userReqClass);
        return resp;

    }
    @GetMapping("/user/login")
    public String login(@RequestParam String identifier,@RequestParam String password){
        return userService.loginUser(identifier, password);
    }






}

package com.userData.userData.Service;

import com.userData.userData.Request.User;
import com.userData.userData.Request.UserReqClass;

public interface UserService {
    String addUser(User user);


    String fetchUser(String userName, String password);

    String changePassword(String userName , String password);

    String addUser(UserReqClass userReqClass);

    String loginUser(String identifier, String password);
}

package com.userData.userData.Repository;

import com.userData.userData.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    @Query("SELECT u FROM UserEntity u WHERE u.userName = :userName")
    Optional<UserEntity> findByExactUserName(@Param("userName") String userName);
    Optional<UserEntity> findByRollNo(@Param("rollNo") Integer rollNo);



}

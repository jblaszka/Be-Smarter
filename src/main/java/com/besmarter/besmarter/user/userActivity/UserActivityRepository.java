package com.besmarter.besmarter.user.userActivity;
import com.besmarter.besmarter.user.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

    List<UserActivity> findAllByUserId(Integer userId);

    @Query("SELECT ua.id, ua.activity, ua.expirationDateString, ua.expirationTimeString, ua.points FROM UserActivity ua WHERE ua.user = :user AND ua.isDone = false")
    List<Object[]> findActivityByUserAndIsNotDone(@Param("user") User user);

    @Query("SELECT ua.activity, ua.expirationDateString, ua.expirationTimeString, ua.points FROM UserActivity ua WHERE ua.user = :user AND ua.isDone = true")
    List<Object[]> findActivityByUserAndIsDone(@Param("user") User user);

    List<UserActivity> findByUser(User user);

    @Modifying
    @Query("UPDATE UserActivity ua SET ua.isDone = true WHERE ua.id = :id")
    void makeDone(@Param("id") Integer id);

}

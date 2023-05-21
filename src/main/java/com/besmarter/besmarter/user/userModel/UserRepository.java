package com.besmarter.besmarter.user.userModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT SUM(ua.points) FROM UserActivity ua WHERE ua.user.id = :userId")
    Integer getTotalPointsByUserId(Integer userId);
}

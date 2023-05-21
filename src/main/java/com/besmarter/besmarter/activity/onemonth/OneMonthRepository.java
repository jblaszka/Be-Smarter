package com.besmarter.besmarter.activity.onemonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public interface OneMonthRepository extends JpaRepository<OneMonth, Integer> {

    @Query("SELECT o.activity FROM OneMonth o WHERE o.id = :id")
    String findActivityById(@Param("id") Integer id);

    @Query("SELECT COUNT(*) FROM OneMonth")
    Long countActivity();

    default String getRandomActivity() {
        Long count = countActivity();
        Integer randomId = new Random().nextInt(count.intValue()) + 1;
        return findActivityById(randomId);
    }
}

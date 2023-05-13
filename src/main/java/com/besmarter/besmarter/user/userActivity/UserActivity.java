package com.besmarter.besmarter.user.userActivity;

import com.besmarter.besmarter.user.userModel.User;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_activity")
public class UserActivity {
    @Id
    @GeneratedValue
    public Integer id;

    public String activity;

    private Integer points;

    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;
}

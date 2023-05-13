package com.besmarter.besmarter.user.userActivity;
import com.besmarter.besmarter.user.userModel.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserActivityRequest {

    public String activity;
    public Integer points;
    public String userEmail;
}

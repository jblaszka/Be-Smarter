package com.besmarter.besmarter.user.userActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserActivityRequest {

    public String activity;
    public Integer points;
    public String userEmail;
}

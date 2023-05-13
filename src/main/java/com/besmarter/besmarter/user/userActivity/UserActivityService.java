package com.besmarter.besmarter.user.userActivity;

import com.besmarter.besmarter.user.config.JwtService;
import com.besmarter.besmarter.user.exception.UserNotFoundException;
import com.besmarter.besmarter.user.userModel.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;

    public void addActivityToUser(AddUserActivityRequest addUserActivityRequest){

        var user = userRepository.findByEmail(addUserActivityRequest.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + addUserActivityRequest.getUserEmail()));

        var userActivity = UserActivity.builder()
                .activity(addUserActivityRequest.getActivity())
                .points(addUserActivityRequest.getPoints())
                .user(user)
                .build();

        userActivityRepository.save(userActivity);
    }
}

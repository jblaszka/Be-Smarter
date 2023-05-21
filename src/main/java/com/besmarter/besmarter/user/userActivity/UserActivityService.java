package com.besmarter.besmarter.user.userActivity;

import com.besmarter.besmarter.user.exception.UserNotFoundException;
import com.besmarter.besmarter.user.userModel.User;
import com.besmarter.besmarter.user.userModel.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;

    public void addActivityToUser(AddUserActivityRequest addUserActivityRequest){
        var user = getUserByEmail(addUserActivityRequest.getUserEmail());

        var userActivity = UserActivity.builder()
                .activity(addUserActivityRequest.getActivity())
                .points(addUserActivityRequest.getPoints())
                .user(user)
                .build();

        calculateExpirationDateTime(userActivity);
        userActivityRepository.save(userActivity);
    }

    public void calculateExpirationDateTime(UserActivity userActivity) {
        int points = userActivity.getPoints();
        LocalDateTime expirationDateTime;
        if (points == 1) {
            expirationDateTime = LocalDateTime.now().plusDays(1);
        } else if (points == 3) {
            expirationDateTime = LocalDateTime.now().plusWeeks(1);
        } else if (points == 5) {
            expirationDateTime = LocalDateTime.now().plusMonths(1);
        } else {
            throw new IllegalArgumentException("Invalid points value: " + points);
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        userActivity.setExpirationTime(expirationDateTime);
        userActivity.setExpirationDateString(expirationDateTime.format(dateFormatter));
        userActivity.setExpirationTimeString(expirationDateTime.format(timeFormatter));
    }

    public List<Object[]> getListActivityToDo(String email) {
        deleteExpiredActivities(email);
        var user = getUserByEmail(email);

        return userActivityRepository.findActivityByUserAndIsNotDone(user);
    }

    public List<Object[]> getListActivityDone(String email) {
        var user = getUserByEmail(email);

        return userActivityRepository.findActivityByUserAndIsDone(user);
    }

    public void deleteExpiredActivities(String email) {
        var user = getUserByEmail(email);

        List<UserActivity> userActivities = userActivityRepository.findByUser(user);
        List<UserActivity> validUserActivities = new ArrayList<>();

        for (UserActivity userActivity : userActivities) {
            LocalDateTime now = LocalDateTime.now();
            if (userActivity.getExpirationTime().isBefore(now)) {
                userActivityRepository.delete(userActivity);
            } else {
                validUserActivities.add(userActivity);
            }
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
    }
}

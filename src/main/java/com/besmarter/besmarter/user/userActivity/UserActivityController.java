package com.besmarter.besmarter.user.userActivity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserActivityController {

    private final UserActivityService userActivityService;

    @PostMapping("/addUserActivity")
    public void addUserActivity(@RequestBody AddUserActivityRequest addUserActivityRequest){
        userActivityService.addActivityToUser(addUserActivityRequest);
    }
}

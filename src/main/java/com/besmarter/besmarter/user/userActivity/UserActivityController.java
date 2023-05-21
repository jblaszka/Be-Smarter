package com.besmarter.besmarter.user.userActivity;
import com.besmarter.besmarter.user.userModel.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-activity")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserActivityController {

    @Autowired
    private final UserActivityRepository userActivityRepository;
    private final UserActivityService userActivityService;

    @PostMapping("/addUserActivity")
    public void addUserActivity(@RequestBody AddUserActivityRequest addUserActivityRequest){
        userActivityService.addActivityToUser(addUserActivityRequest);
    }

    @GetMapping("/{user}")
    public List getAll(@PathVariable(required = false) User user) {
        return userActivityRepository.findActivityByUserAndIsNotDone(user);
    }

    @PostMapping("/makeDone/{id}")
    @Transactional
    public ResponseEntity<Void> makeDone(@PathVariable(required = false) Integer id) {
        userActivityRepository.makeDone(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("done/{email}")
    public List getActivityDone(@PathVariable(required = false) String email) {
        return userActivityService.getListActivityDone(email);
    }

    @GetMapping("/toDo/{email}")
    public List getActivityToDo(@PathVariable(required = false) String email) {
        return userActivityService.getListActivityToDo(email);
    }
}

package com.besmarter.besmarter.activity.oneweek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/one-week")
@CrossOrigin("*")
public class OneWeekController {

    @Autowired
    OneWeekRepository oneWeekRepository;

    @GetMapping("/{id}")
    public ResponseEntity<String> getActivityById(@PathVariable(required = false) Integer id) {
        String activity = oneWeekRepository.findActivityById(id);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomActivity() {
        String activity = oneWeekRepository.getRandomActivity();
        return ResponseEntity.ok(activity);
    }
}

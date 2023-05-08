package com.besmarter.besmarter.activity.oneday;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/one-day")
@CrossOrigin("*")
public class OneDayController {

    @Autowired
    OneDayRepository oneDayRepository;

    @GetMapping("/{id}")
    public ResponseEntity<String> getActivityById(@PathVariable(required = false) Integer id) {
        String activity = oneDayRepository.findActivityById(id);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomActivity() {
        String activity = oneDayRepository.getRandomActivity();
        return ResponseEntity.ok(activity);
    }
}

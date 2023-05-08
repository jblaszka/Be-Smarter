package com.besmarter.besmarter.activity.onemonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/one-month")
@CrossOrigin("*")
public class OneMonthController {

    @Autowired
    OneMonthRepository oneMonthRepository;

    @GetMapping("/{id}")
    public ResponseEntity<String> getActivityById(@PathVariable(required = false) Integer id) {
        String activity = oneMonthRepository.findActivityById(id);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomActivity() {
        System.out.println("Jestem tutaj");
        String activity = oneMonthRepository.getRandomActivity();
        System.out.println(activity);
        return ResponseEntity.ok(activity);
    }
}

package com.besmarter.besmarter.user.demo;

import com.besmarter.besmarter.activity.oneday.OneDayRepository;
import com.besmarter.besmarter.activity.oneweek.OneWeekController;
import com.besmarter.besmarter.activity.oneweek.OneWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@CrossOrigin("*")
public class DemoController {

    @Autowired
    OneWeekRepository oneWeekRepository;

    public DemoController(OneWeekRepository oneWeekRepository) {
        this.oneWeekRepository = oneWeekRepository;
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/la")
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("Kicia");
    }

}
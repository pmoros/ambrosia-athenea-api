package com.ambrosia.athenea.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.athenea.models.AcademicHistory;
import com.ambrosia.athenea.models.User;
import com.ambrosia.athenea.repositories.AcademicHistoryRepository;
import com.ambrosia.athenea.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AcademicHistoryRepository academicHistoryRepository;

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        List<AcademicHistory> academicHistories = user.getAcademicHistories();
        user.setAcademicHistories(null);
        userRepository.save(user);
        if (academicHistories != null) {
            academicHistories.forEach(academicHistory -> {
                academicHistory.setStudent(user);
                academicHistoryRepository.save(academicHistory);
            });
        }
        return userRepository.findByCode(user.getCode());
    }
}

package com.ambrosia.athenea.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.athenea.models.AcademicHistory;
import com.ambrosia.athenea.models.CourseGroup;
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

    @GetMapping("course-groups/{userCode}")
    public List<CourseGroup> getCourseGroups(@PathVariable String userCode) {
        List<CourseGroup> courseGroups = new ArrayList<>();
        User user = userRepository.findByCode(userCode);

        if (user == null) {
            return null;
        }

        List<CourseGroup> professorCourseGroups = user.getCourseGroups();
        if (professorCourseGroups.size() > 0) {
            for (int i = 0; i < professorCourseGroups.size(); i++) {
                CourseGroup courseGroup = professorCourseGroups.get(i);
                courseGroup.setAcademicHistories(new ArrayList<AcademicHistory>());

                courseGroups.add(courseGroup);
            }
            return courseGroups;
        }

        List<AcademicHistory> academicHistories = user.getAcademicHistories();
        if (academicHistories.size() > 0) {
            List<CourseGroup> studentCourseGroups = new ArrayList<CourseGroup>();
            academicHistories.forEach(academicHistory -> {
                List<CourseGroup> courseGroupsFromAcademicHistory = academicHistory.getCourseGroups();
                for (int i = 0; i < courseGroupsFromAcademicHistory.size(); i++) {
                    CourseGroup courseGroup = courseGroupsFromAcademicHistory.get(i);
                    courseGroup.setAcademicHistories(new ArrayList<AcademicHistory>());
                    studentCourseGroups.add(courseGroup);
                }
            });
            return studentCourseGroups;
        }

        List<CourseGroup> emptyCourseGroups = new ArrayList<CourseGroup>();
        return emptyCourseGroups;
    }
}

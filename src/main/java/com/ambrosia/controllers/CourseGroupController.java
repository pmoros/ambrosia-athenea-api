package com.ambrosia.controllers;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseGroupController {

    @Autowired
    private CourseGroupRepository courseGroupRepository;

    @PostMapping("/inscriptions/course-group")
    public String createCourseGroup(@RequestBody CourseGroup courseGroup) {
        this.courseGroupRepository.save(courseGroup);
        return "CourseGroup created";
    }

    // enpoint to get all course groups
    // public void getAllCourseGroups() {

    // }

}

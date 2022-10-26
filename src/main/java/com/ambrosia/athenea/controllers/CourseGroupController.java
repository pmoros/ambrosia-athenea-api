package com.ambrosia.athenea.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.athenea.models.CourseGroup;
import com.ambrosia.athenea.repositories.CourseGroupRepository;

@RestController
@RequestMapping("/course-groups")
public class CourseGroupController {

    @Autowired
    CourseGroupRepository courseGroupRepository;

    Logger logger = LoggerFactory.getLogger(CourseGroupController.class);

    @PostMapping("")
    public List<CourseGroup> createCourseGroup(@RequestBody List<CourseGroup> courseGroups) {
        ArrayList<CourseGroup> createdCourseGroups = new ArrayList<CourseGroup>();
        for (CourseGroup courseGroup : courseGroups) {
            try {
                courseGroupRepository.save(courseGroup);
                createdCourseGroups.add(courseGroup);
            } catch (DataIntegrityViolationException e) {
                logger.error("CourseGroup already exists: " + courseGroup.getCode());
            }
        }
        return createdCourseGroups;
    }

    @GetMapping("/{courseCode}")
    public List<CourseGroup> getCourseGroupsByCourseCode(@PathVariable String courseCode) {
        return courseGroupRepository.findByCourseCode(courseCode);
    }
}

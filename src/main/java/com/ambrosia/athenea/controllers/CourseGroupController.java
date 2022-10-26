package com.ambrosia.athenea.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.athenea.models.CourseGroup;
import com.ambrosia.athenea.models.Schedule;
import com.ambrosia.athenea.repositories.CourseGroupRepository;
import com.ambrosia.athenea.repositories.ScheduleRepository;
import com.ambrosia.athenea.repositories.UserRepository;

@RestController
@RequestMapping("/course-groups")
public class CourseGroupController {

    @Autowired
    CourseGroupRepository courseGroupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    Logger logger = LoggerFactory.getLogger(CourseGroupController.class);

    @PostMapping("")
    public List<CourseGroup> createCourseGroup(@RequestBody List<CourseGroup> courseGroups) {
        ArrayList<CourseGroup> createdCourseGroups = new ArrayList<CourseGroup>();
        for (CourseGroup courseGroup : courseGroups) {
            try {
                try {
                    userRepository.save(courseGroup.getProfessor());
                } catch (DataIntegrityViolationException e) {
                    logger.error("User already exists: " + courseGroup.getCode());
                }
                courseGroupRepository.save(courseGroup);
                for (Schedule schedule : courseGroup.getSchedules()) {
                    schedule.setCourseGroup(courseGroup);
                    scheduleRepository.save(schedule);
                }
                createdCourseGroups.add(courseGroup);
            } catch (Exception e) {
                logger.error("Error creating course group: " + courseGroup.getCode());
            }
        }

        return createdCourseGroups;
    }

    @GetMapping("/{courseCode}")
    public List<CourseGroup> getCourseGroupsByCourseCode(@PathVariable String courseCode) {
        List<CourseGroup> courses = courseGroupRepository.findByCourseCode(courseCode);
        return courses;
    }
}

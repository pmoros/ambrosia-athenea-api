package com.ambrosia.controllers;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.models.Schedule;
import com.ambrosia.repositories.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// TODO: Add PUT and DELETE methods
@RestController
@RequestMapping("/course-groups")
public class CourseGroupController {
    @Autowired
    private CourseGroupRepository courseGroupRepository;

    Logger logger = LoggerFactory.getLogger(CourseGroupController.class);

    @GetMapping("")
    public Iterable<CourseGroup> getCourseGroups() {
        return courseGroupRepository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseGroup createCourseGroup(@RequestBody CourseGroup courseGroup) {
        courseGroup = this.courseGroupRepository.save(courseGroup);
        return courseGroup;
    }

    @PutMapping("/schedules")
    @ResponseStatus(HttpStatus.ACCEPTED)
    /*
     * Both fields are needed to find the course group.
     */
    public CourseGroup addScheduleToCourseGroup(@RequestParam("courseCode") String courseCode,
            @RequestParam("courseGroupCode") String courseGroupCode, @RequestBody List<Schedule> schedules) {
        CourseGroup courseGroupFromDB = (this.courseGroupRepository.findByCourseCodeAndCourseGroupCode(courseCode,
                courseGroupCode)).get(0);

        // Remove all schedules from the course group
        courseGroupFromDB.getSchedules().clear();
        this.courseGroupRepository.save(courseGroupFromDB);

        // Add new schedules to the course group
        schedules.forEach(schedule -> schedule.setCourseGroup(courseGroupFromDB));
        courseGroupFromDB.setSchedules(schedules);
        this.courseGroupRepository.save(courseGroupFromDB);
        return courseGroupFromDB;
    }

}

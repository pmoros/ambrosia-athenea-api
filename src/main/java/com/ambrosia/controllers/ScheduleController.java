package com.ambrosia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.models.Enrollment;
import com.ambrosia.models.Schedule;
import com.ambrosia.repositories.CourseGroupRepository;
import com.ambrosia.repositories.EnrollmentRepository;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    CourseGroupRepository courseGroupRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @GetMapping("/professors")
    public List<Schedule> getProfessorSchedule(@RequestParam("professorUsername") Optional<String> professorUsername) {
        List<CourseGroup> courseGroups = courseGroupRepository.findByProfessorUsername(professorUsername.get());
        List<Schedule> schedules = new ArrayList<Schedule>();
        for (CourseGroup courseGroup : courseGroups) {
            schedules.addAll(courseGroup.getSchedules());
        }
        return schedules;
    }

    @GetMapping("/students")
    public List<Schedule> getStudentSchedule(@RequestParam("studentUsername") Optional<String> studentUsername) {
        // TODO: Refactor this to use a service
        ArrayList<CourseGroup> courseGroups = new ArrayList<CourseGroup>();
        List<Enrollment> enrollments = enrollmentRepository
                .findAllByStudentUsername(studentUsername);

        for (Enrollment e : enrollments) {
            courseGroups.addAll(e.getCourseGroups());
        }

        List<Schedule> schedules = new ArrayList<Schedule>();
        for (CourseGroup courseGroup : courseGroups) {
            schedules.addAll(courseGroup.getSchedules());
        }
        return schedules;

    }

}

package com.ambrosia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.models.Enrollment;
import com.ambrosia.repositories.CourseGroupRepository;
import com.ambrosia.repositories.EnrollmentRepository;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseGroupRepository courseGroupRepository;

    @PostMapping("/students")
    public void enrollCourseGroup(@RequestBody Enrollment enrollment) {
        Enrollment enrollmentToSave = new Enrollment();
        enrollmentToSave.setStudentUsername(enrollment.getStudentUsername());
        enrollmentToSave.setAcademicHistoryCode(enrollment.getAcademicHistoryCode());
        enrollmentToSave.setCourseGroups(new ArrayList<CourseGroup>());

        enrollmentToSave.getCourseGroups()
                .addAll(enrollment
                        .getCourseGroups()
                        .stream()
                        .map(c -> {
                            CourseGroup cg = (CourseGroup) courseGroupRepository
                                    .findByCourseCodeAndCourseGroupCode(c.getCourseCode(), c.getCourseGroupCode())
                                    .get(0);
                            cg.getEnrollments().add(enrollmentToSave);
                            // courseGroupRepository.save(cg);
                            return cg;
                        }).collect(Collectors.toList()));

        enrollmentRepository.save(enrollmentToSave);
    }

}

package com.ambrosia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                            cg.setTaken(Integer.toString(Integer.parseInt(cg.getTaken()) + 1));
                            return cg;
                        }).collect(Collectors.toList()));

        enrollmentRepository.save(enrollmentToSave);
    }

    @GetMapping("/professors/enrolled-students")
    public List<Enrollment> getStudentsEnrolledInCourseGroup(@RequestParam("courseName") Optional<String> courseName,
            @RequestParam("courseCode") Optional<String> courseCode,
            @RequestParam("courseGroupCode") Optional<String> courseGroupCode) {
        List<CourseGroup> courseGroups = courseGroupRepository
                .findAllByCourseCodeOrCourseGroupCodeOrCourseName(courseCode, courseGroupCode, courseName);
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        for (CourseGroup cg : courseGroups) {
            enrollments.addAll(cg.getEnrollments());
        }

        for (Enrollment e : enrollments) {
            e.setCourseGroups(new ArrayList<CourseGroup>());
        }

        return enrollments;
    }

}

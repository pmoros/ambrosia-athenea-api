package com.ambrosia.athenea.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ambrosia.athenea.models.AcademicHistory;
import com.ambrosia.athenea.models.CourseGroup;
import com.ambrosia.athenea.repositories.AcademicHistoryRepository;
import com.ambrosia.athenea.repositories.CourseGroupRepository;
import com.ambrosia.athenea.repositories.UserRepository;

@Controller
@Transactional
public class EnrollmentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseGroupRepository courseGroupRepository;

    @Autowired
    AcademicHistoryRepository academicHistoryRepository;

    public boolean createEnrollment(String studentCode, String academicHistoryCode, String[] courseGroupsCodes) {
        AcademicHistory academicHistory = academicHistoryRepository.findByCode(academicHistoryCode);
        List<CourseGroup> courseGroups = courseGroupRepository.findByCodeIn(courseGroupsCodes);
        try {
            if (academicHistory != null && courseGroups != null) {
                return academicHistory.getCourseGroups().addAll(courseGroups);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

}

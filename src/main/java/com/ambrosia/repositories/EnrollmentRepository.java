package com.ambrosia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findAllByStudentUsernameAndAcademicHistoryCode(String studentUsername,
            String academicHistoryCode);

    List<Enrollment> findAllByStudentUsername(Optional<String> courseName);
}
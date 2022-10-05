package com.ambrosia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findAllByStudentUsernameAndAcademicHistoryCode(String studentUsername,
            String academicHistoryCode);
}
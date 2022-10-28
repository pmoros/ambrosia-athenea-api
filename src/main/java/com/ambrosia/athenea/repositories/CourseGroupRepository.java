package com.ambrosia.athenea.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambrosia.athenea.models.CourseGroup;

public interface CourseGroupRepository extends JpaRepository<CourseGroup, Long> {
    List<CourseGroup> findByCourseCode(String courseCode);

    CourseGroup findByCode(String code);

    List<CourseGroup> findByCodeIn(String[] courseGroupsCodes);
}
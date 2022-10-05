package com.ambrosia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.CourseGroup;

public interface CourseGroupRepository extends CrudRepository<CourseGroup, Long> {

    List<CourseGroup> findByCourseCodeAndCourseGroupCode(String courseCode, String courseGroupCode);

    // ! TODO: fix returns empty list when courseGroupCode and courseCode are null
    List<CourseGroup> findAllByCourseCodeOrCourseGroupCodeOrCourseName(Optional<String> courseCode,
            Optional<String> courseGroupCode,
            Optional<String> courseName);

    List<CourseGroup> findAllByProfessorUsername(String professorUsername);

    List<CourseGroup> findByProfessorUsername(String professorUsername);

}
package com.ambrosia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.CourseGroup;

public interface CourseGroupRepository extends CrudRepository<CourseGroup, Long> {

}
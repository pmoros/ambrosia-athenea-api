package com.ambrosia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

}
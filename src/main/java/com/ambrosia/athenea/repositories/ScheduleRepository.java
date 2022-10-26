package com.ambrosia.athenea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambrosia.athenea.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
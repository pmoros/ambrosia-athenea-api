package com.ambrosia.athenea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambrosia.athenea.models.AcademicHistory;

public interface AcademicHistoryRepository extends JpaRepository<AcademicHistory, Long> {

    AcademicHistory findByCode(String code);

}

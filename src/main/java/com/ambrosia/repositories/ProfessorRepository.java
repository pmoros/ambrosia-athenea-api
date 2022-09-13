package com.ambrosia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ambrosia.models.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

    Professor findByProfessorCode(String professorCode);

}
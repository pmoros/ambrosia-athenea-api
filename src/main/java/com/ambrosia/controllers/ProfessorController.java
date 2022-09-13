package com.ambrosia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.ambrosia.models.Professor;
import com.ambrosia.repositories.ProfessorRepository;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/inscriptions/professors")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }

}

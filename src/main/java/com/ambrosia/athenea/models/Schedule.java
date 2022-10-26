package com.ambrosia.athenea.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String day;
    private String building;
    private String classroom;
    private String timeOfStart;
    private String timeOfEnd;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_group_id", referencedColumnName = "id")
    private CourseGroup courseGroup;
}

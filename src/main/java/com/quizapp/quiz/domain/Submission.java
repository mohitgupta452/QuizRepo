package com.quizapp.quiz.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false) private Instant submittedAt = Instant.now();
    @Column(nullable = false) private int totalQuestions;
    @Column(nullable = false) private int autoScore; // TEXT excluded from auto-score

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionAnswer> answers = new ArrayList<>();

    // getters/setters
}
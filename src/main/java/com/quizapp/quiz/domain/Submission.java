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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Instant submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getAutoScore() {
        return autoScore;
    }

    public void setAutoScore(int autoScore) {
        this.autoScore = autoScore;
    }

    public List<SubmissionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SubmissionAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", submittedAt=" + submittedAt +
                ", totalQuestions=" + totalQuestions +
                ", autoScore=" + autoScore +
                ", answers=" + answers +
                '}';
    }
}
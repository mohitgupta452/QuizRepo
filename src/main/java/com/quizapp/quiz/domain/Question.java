package com.quizapp.quiz.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private QuestionType type;

    @Column(nullable = false, length = 1000)
    private String prompt;

    // For TEXT/TRUE_FALSE/MCQ scoring:
    // - MCQ stores correct option id (filled after options persisted)
    // - TRUE_FALSE stores "true" or "false" as text
    // - TEXT can be nullable -> manual or no auto-scoring
    @Column(name = "correct_text", length = 1000)
    private String correctText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "o_order")
    private List<OptionChoice> options = new ArrayList<>();

    @Column(name = "correct_option_id")
    private Long correctOptionId;

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

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCorrectText() {
        return correctText;
    }

    public void setCorrectText(String correctText) {
        this.correctText = correctText;
    }

    public List<OptionChoice> getOptions() {
        return options;
    }

    public void setOptions(List<OptionChoice> options) {
        this.options = options;
    }

    public Long getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(Long correctOptionId) {
        this.correctOptionId = correctOptionId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", type=" + type +
                ", prompt='" + prompt + '\'' +
                ", correctText='" + correctText + '\'' +
                ", options=" + options +
                ", correctOptionId=" + correctOptionId +
                '}';
    }
}
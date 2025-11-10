package com.quizapp.quiz.dto;

import java.util.List;

public class QuizViewDTO {
    private Long id;
    private String title;
    private List<QuestionViewDTO> questions;

    public QuizViewDTO() {
    }

    public QuizViewDTO(Long id, String title, List<QuestionViewDTO> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionViewDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionViewDTO> questions) {
        this.questions = questions;
    }
}

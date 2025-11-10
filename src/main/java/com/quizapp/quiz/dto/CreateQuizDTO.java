package com.quizapp.quiz.dto;

import java.util.List;

public class CreateQuizDTO {
    private String title;
    private List<CreateQuestionDTO> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CreateQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionDTO> questions) {
        this.questions = questions;
    }
}

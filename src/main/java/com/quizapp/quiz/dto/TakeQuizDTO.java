package com.quizapp.quiz.dto;

import java.util.List;

public class TakeQuizDTO {
    private List<AnswerDTO> answers;

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}

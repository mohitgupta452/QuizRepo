package com.quizapp.quiz.dto;

import java.util.List;

public class CreateQuestionDTO {
    private String type;         // MCQ | TRUE_FALSE | TEXT
    private String prompt;
    private List<String> options; // for MCQ
    private String correctText;   // TRUE_FALSE ("true"/"false") or TEXT (optional)

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectText() {
        return correctText;
    }

    public void setCorrectText(String correctText) {
        this.correctText = correctText;
    }
}

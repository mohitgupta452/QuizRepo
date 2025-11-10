package com.quizapp.quiz.dto;

import java.util.List;

public class QuestionViewDTO {
    private Long id;
    private String type;
    private String prompt;
    private List<OptionViewDTO> options;

    public QuestionViewDTO() {
    }

    public QuestionViewDTO(Long id, String type, String prompt, List<OptionViewDTO> options) {
        this.id = id;
        this.type = type;
        this.prompt = prompt;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<OptionViewDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionViewDTO> options) {
        this.options = options;
    }
}

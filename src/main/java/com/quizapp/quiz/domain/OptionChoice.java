package com.quizapp.quiz.domain;

import javax.persistence.*;

@Entity
public class OptionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false, length = 500)
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "OptionChoice{" +
                "id=" + id +
                ", question=" + question +
                ", text='" + text + '\'' +
                '}';
    }
}
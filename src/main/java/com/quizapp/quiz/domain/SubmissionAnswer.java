package com.quizapp.quiz.domain;

import javax.persistence.*;

@Entity
public class SubmissionAnswer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "submission_id")
    private Submission submission;

    @ManyToOne(optional = false) @JoinColumn(name = "question_id")
    private Question question;

    // One of these will be filled depending on type
    @Column(name = "selected_option_id") private Long selectedOptionId; // for MCQ
    @Column(name = "answer_text", length = 1000) private String answerText; // for TEXT/TRUE_FALSE

    @Column(nullable = false) private boolean correct; // auto-evaluated for MCQ/TRUE_FALSE

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(Long selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "SubmissionAnswer{" +
                "id=" + id +
                ", submission=" + submission +
                ", question=" + question +
                ", selectedOptionId=" + selectedOptionId +
                ", answerText='" + answerText + '\'' +
                ", correct=" + correct +
                '}';
    }
}

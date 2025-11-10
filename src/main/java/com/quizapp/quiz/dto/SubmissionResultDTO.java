package com.quizapp.quiz.dto;

public class SubmissionResultDTO {
    private Long submissionId;
    private int totalQuestions;
    private int autoScore;

    public SubmissionResultDTO() {
    }

    public SubmissionResultDTO(Long submissionId, int totalQuestions, int autoScore) {
        this.submissionId = submissionId;
        this.totalQuestions = totalQuestions;
        this.autoScore = autoScore;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
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
}

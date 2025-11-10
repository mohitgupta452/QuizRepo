package com.quizapp.quiz.service;

import com.quizapp.quiz.dto.CreateQuizDTO;
import com.quizapp.quiz.dto.QuizViewDTO;
import com.quizapp.quiz.dto.SubmissionResultDTO;
import com.quizapp.quiz.dto.TakeQuizDTO;

public interface QuizService {
    QuizViewDTO createQuiz(CreateQuizDTO req);
    QuizViewDTO getQuiz(Long id);
    SubmissionResultDTO submit(Long quizId, TakeQuizDTO answers);
}
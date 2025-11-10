package com.quizapp.quiz.controller;

import com.quizapp.quiz.dto.CreateQuizDTO;
import com.quizapp.quiz.dto.QuizViewDTO;
import com.quizapp.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/quizzes")
public class AdminQuizController {
    private final QuizService quizService;

    public AdminQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizViewDTO create(@RequestBody CreateQuizDTO req) {
        return quizService.createQuiz(req);
    }
}

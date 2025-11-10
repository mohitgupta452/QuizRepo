package com.quizapp.quiz.controller;

import com.quizapp.quiz.dto.QuizViewDTO;
import com.quizapp.quiz.dto.SubmissionResultDTO;
import com.quizapp.quiz.dto.TakeQuizDTO;
import com.quizapp.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/quizzes")
public class PublicQuizController {
    private final QuizService quizService;
    public PublicQuizController(QuizService quizService) { this.quizService = quizService; }

    @GetMapping("/{id}")
    public QuizViewDTO get(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("/{id}/submissions")
    public SubmissionResultDTO submit(@PathVariable Long id, @RequestBody TakeQuizDTO answers) {
        return quizService.submit(id, answers);
    }
}

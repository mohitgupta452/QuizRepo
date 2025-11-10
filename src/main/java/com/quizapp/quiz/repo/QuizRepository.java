package com.quizapp.quiz.repo;

import com.quizapp.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByTitleIgnoreCase(String title);

}

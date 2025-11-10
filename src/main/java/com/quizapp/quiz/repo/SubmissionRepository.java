package com.quizapp.quiz.repo;

import com.quizapp.quiz.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}

package com.quizapp.quiz.service.impl;

import com.quizapp.quiz.domain.*;
import com.quizapp.quiz.dto.*;
import com.quizapp.quiz.repo.QuestionRepository;
import com.quizapp.quiz.repo.QuizRepository;
import com.quizapp.quiz.repo.SubmissionRepository;
import com.quizapp.quiz.service.QuizService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;
    private final SubmissionRepository submissionRepo;

    public QuizServiceImpl(QuizRepository quizRepo, QuestionRepository questionRepo, SubmissionRepository submissionRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
        this.submissionRepo = submissionRepo;
    }

    @Override
    public QuizViewDTO createQuiz(CreateQuizDTO req) {
        Quiz quiz = new Quiz();
        quiz.setTitle(req.getTitle());

        if (req.getQuestions() != null) {
            for (CreateQuestionDTO qdto : req.getQuestions()) {
                Question q = new Question();
                q.setQuiz(quiz);
                q.setType(QuestionType.valueOf(qdto.getType()));
                q.setPrompt(qdto.getPrompt());
                q.setCorrectText(qdto.getCorrectText());
                quiz.getQuestions().add(q);

                if (q.getType() == QuestionType.MCQ && qdto.getOptions() != null) {
                    List<OptionChoice> opts = new ArrayList<>();
                    for (String text : qdto.getOptions()) {
                        OptionChoice oc = new OptionChoice();
                        oc.setQuestion(q);
                        oc.setText(text);
                        opts.add(oc);
                    }
                    q.setOptions(opts);
                }
            }
        }

        Quiz saved = quizRepo.save(quiz);

        // default: first option is correct for MCQ if not specified
        for (Question q : saved.getQuestions()) {
            if (q.getType() == QuestionType.MCQ && q.getCorrectOptionId() == null && !q.getOptions().isEmpty()) {
                q.setCorrectOptionId(q.getOptions().get(0).getId());
            }
        }
        saved = quizRepo.save(saved);

        return mapQuiz(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizViewDTO getQuiz(Long id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return mapQuiz(quiz);
    }

    @Override
    public SubmissionResultDTO submit(Long quizId, TakeQuizDTO payload) {
        Quiz quiz = quizRepo.findById(quizId).orElseThrow(NoSuchElementException::new);

        Map<Long, Question> byId = new HashMap<>();
        for (Question q : quiz.getQuestions()) byId.put(q.getId(), q);

        Submission sub = new Submission();
        sub.setQuiz(quiz);
        sub.setTotalQuestions(quiz.getQuestions().size());

        int score = 0;
        if (payload.getAnswers() != null) {
            for (AnswerDTO adto : payload.getAnswers()) {
                Question q = byId.get(adto.getQuestionId());
                if (q == null) continue;

                SubmissionAnswer sa = new SubmissionAnswer();
                sa.setSubmission(sub);
                sa.setQuestion(q);

                boolean correct = false;
                switch (q.getType()) {
                    case MCQ:
                        sa.setSelectedOptionId(adto.getSelectedOptionId());
                        correct = Objects.equals(q.getCorrectOptionId(), adto.getSelectedOptionId());
                        break;
                    case TRUE_FALSE:
                        sa.setAnswerText(adto.getAnswerText());
                        if (q.getCorrectText() != null) {
                            correct = q.getCorrectText().equalsIgnoreCase(adto.getAnswerText());
                        }
                        break;
                    case TEXT:
                        sa.setAnswerText(adto.getAnswerText());
                        break;
                }
                sa.setCorrect(correct);
                if (q.getType() != QuestionType.TEXT && correct) score++;

                sub.getAnswers().add(sa);
            }
        }

        sub.setAutoScore(score);
        Submission saved = submissionRepo.save(sub);
        return new SubmissionResultDTO(saved.getId(), saved.getTotalQuestions(), saved.getAutoScore());
    }

    private QuizViewDTO mapQuiz(Quiz q) {
        List<QuestionViewDTO> qv = new ArrayList<>();
        for (Question qq : q.getQuestions()) {
            List<OptionViewDTO> ov = new ArrayList<>();
            if (qq.getType() == QuestionType.MCQ) {
                for (OptionChoice o : qq.getOptions()) {
                    ov.add(new OptionViewDTO(o.getId(), o.getText()));
                }
            }
            qv.add(new QuestionViewDTO(qq.getId(), qq.getType().name(), qq.getPrompt(), ov));
        }
        return new QuizViewDTO(q.getId(), q.getTitle(), qv);
    }
}

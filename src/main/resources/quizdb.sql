CREATE DATABASE quizdb;

CREATE TABLE quiz (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE question (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quiz_id BIGINT NOT NULL,
  type VARCHAR(20) NOT NULL,
  prompt VARCHAR(1000) NOT NULL,
  correct_text VARCHAR(1000),
  correct_option_id BIGINT,
  q_order INT,
  CONSTRAINT fk_question_quiz FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

CREATE TABLE option_choice (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  question_id BIGINT NOT NULL,
  text VARCHAR(500) NOT NULL,
  o_order INT,
  CONSTRAINT fk_option_question FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

CREATE TABLE submission (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quiz_id BIGINT NOT NULL,
  submitted_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  total_questions INT NOT NULL,
  auto_score INT NOT NULL,
  CONSTRAINT fk_submission_quiz FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

CREATE TABLE submission_answer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  submission_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  selected_option_id BIGINT,
  answer_text VARCHAR(1000),
  correct BIT(1) NOT NULL,
  CONSTRAINT fk_sa_submission FOREIGN KEY (submission_id) REFERENCES submission(id) ON DELETE CASCADE,
  CONSTRAINT fk_sa_question FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

CREATE INDEX idx_question_quiz ON question(quiz_id);
CREATE INDEX idx_option_question ON option_choice(question_id);
CREATE INDEX idx_submissions_quiz ON submission(quiz_id);


INSERT INTO quiz (id, title) VALUES (1, 'Java Basics');

INSERT INTO question (id, quiz_id, type, prompt, correct_text, correct_option_id, q_order) VALUES
  (10, 1, 'MCQ', 'Default value of int field in a class?', NULL, NULL, 0),
  (11, 1, 'TRUE_FALSE', 'String is immutable in Java', 'true', NULL, 1),
  (12, 1, 'TEXT', 'Explain JVM vs JRE', NULL, NULL, 2);

INSERT INTO option_choice (id, question_id, text, o_order) VALUES
  (101, 10, '0', 0),
  (102, 10, '1', 1),
  (103, 10, 'null', 2);

UPDATE question SET correct_option_id = 101 WHERE id = 10;
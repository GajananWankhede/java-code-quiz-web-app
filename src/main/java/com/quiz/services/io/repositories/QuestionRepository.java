package com.quiz.services.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quiz.services.io.entities.AnswerEntity;
import com.quiz.services.io.entities.QuestionEntity;
@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    QuestionEntity findById(long id);
    QuestionEntity findByQuestionContent(String questionContent);
    AnswerEntity findByAnswers(String answerContent);
}

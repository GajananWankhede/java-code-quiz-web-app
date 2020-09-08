package com.quiz.services.service.serviceInterfaces;


import java.util.Set;

import com.quiz.services.shared.dto.AnswerDto;
import com.quiz.services.shared.dto.QuestionDto;
public interface QuestionService {

    QuestionDto createQuestion(QuestionDto questionDto);
    Set<QuestionDto> getAllQuestions();
    QuestionDto getQuestionById(long id);
    QuestionDto updateAnswer(AnswerDto answerDto, long id);
    String deleteQuestionById(long id);
}

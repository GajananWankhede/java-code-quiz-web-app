package com.quiz.services.service.serviceInterfaces.implementations;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.quiz.services.io.entities.QuestionEntity;
import com.quiz.services.io.repositories.QuestionRepository;
import com.quiz.services.service.serviceInterfaces.QuestionService;
import com.quiz.services.shared.dto.AnswerDto;
import com.quiz.services.shared.dto.QuestionDto;

import java.util.HashSet;
import java.util.Set;
@Service
public class QuestionServiceImplementation implements QuestionService {

    private QuestionRepository _questionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public QuestionServiceImplementation(QuestionRepository questionRepository){
        _questionRepository = questionRepository;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto question) {

        if(_questionRepository.findByQuestionContent(question.getQuestionContent()) != null)
            throw new RuntimeException("Question already exists in Database");
        
        for(AnswerDto answer: question.getAnswers()){
            answer.setQuestion(question);
        }

        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
        QuestionDto returnValue = modelMapper.map(_questionRepository.save(questionEntity), QuestionDto.class);

        return returnValue;
    }

    @Override
    public QuestionDto getQuestionById(long id) {

        return modelMapper.map(_questionRepository.findById(id), QuestionDto.class);
    }


    @Override
    public String deleteQuestionById(long id) {
        _questionRepository.deleteById(id);

        return "Operation successful";
    }

    @Override
    public Set<QuestionDto> getAllQuestions() {
        Set<QuestionDto> returnSet = new HashSet<>();
        for(QuestionEntity question: _questionRepository.findAll()){
            QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
            returnSet.add(questionDto);
        }
        return returnSet;
    }

    @Override
    public QuestionDto updateAnswer(AnswerDto answer, long id) {
        QuestionEntity question = _questionRepository.findById(id);
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.getAnswers().add(answer);
        QuestionEntity returnQuestion = _questionRepository.save(modelMapper.map(questionDto, QuestionEntity.class));

        return modelMapper.map(returnQuestion, QuestionDto.class);
    }

}

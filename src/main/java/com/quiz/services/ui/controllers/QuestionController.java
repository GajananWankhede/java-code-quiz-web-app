package com.quiz.services.ui.controllers;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.services.service.serviceInterfaces.QuestionService;
import com.quiz.services.shared.dto.AnswerDto;
import com.quiz.services.shared.dto.QuestionDto;
import com.quiz.services.ui.models.request.AnswerRequestModel;
import com.quiz.services.ui.models.request.QuestionRequestModel;
import com.quiz.services.ui.models.response.QuestionResponseModel;
@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("quiz-app") 
public class QuestionController {

    @Autowired
    QuestionService questionService;

    private ModelMapper modelMapper = new ModelMapper();

    
    @PostMapping("/saveQuestion")
    public QuestionResponseModel createQuestion(@RequestBody QuestionRequestModel question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

        return modelMapper.map(questionService.createQuestion(questionDto), QuestionResponseModel.class);

    }

    @GetMapping("getQuestionById/{id}")
    public QuestionResponseModel getQuestionById(@PathVariable long id){
        return modelMapper.map(questionService.getQuestionById(id), QuestionResponseModel.class);
    }

   
    @GetMapping("/getAllQuestions")
    public Set<QuestionResponseModel> getAllQuestions(){
        Set<QuestionResponseModel> returnValue = new HashSet<>();
        for(QuestionDto questionDto: questionService.getAllQuestions()){
            QuestionResponseModel questionResponseModel= modelMapper.map(questionDto, QuestionResponseModel.class);
            returnValue.add(questionResponseModel);
        }

        return returnValue;
    }
    
    @DeleteMapping("/deleteQuestionById/{id}")
    public String deleteQuestionById(@PathVariable long id){

        questionService.deleteQuestionById(id);
        return "Question was successfully deleted";

    }

    @PutMapping("addAnswerToQuestion/{id}")
    public QuestionResponseModel addAnswerToQuestion(@PathVariable long id, @RequestBody AnswerRequestModel answer){

        AnswerDto answerToAdd = modelMapper.map(answer, AnswerDto.class);

        return modelMapper.map(questionService.updateAnswer(answerToAdd, id), QuestionResponseModel.class);

    }


}

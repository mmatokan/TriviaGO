package hr.fer.ruazosa.networkquiz.controller;

import hr.fer.ruazosa.networkquiz.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionService questionService;
}

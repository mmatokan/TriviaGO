package hr.fer.ruazosa.networkquiz.service;

import hr.fer.ruazosa.networkquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements IQuestionService{

    @Autowired
    private QuestionRepository questionRepository;
}

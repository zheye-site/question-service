package com.zheye.question.rest;

import com.zheye.question.domain.application.QuestionApplicationService;
import com.zheye.question.domain.application.command.CreateQuestionCommand;
import com.zheye.question.rest.request.CreateQuestionRequest;
import com.zheye.question.rest.response.QuestionCreatedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionCommandRestController {

    private final QuestionApplicationService questionApplicationService;

    public QuestionCommandRestController(QuestionApplicationService questionApplicationService) {
        this.questionApplicationService = questionApplicationService;
    }

    @PostMapping("/create-question")
    public QuestionCreatedResponse createQuestion(@RequestBody CreateQuestionRequest request) {
        var command = new CreateQuestionCommand(request.questionerId(), request.title(), request.detail());
        var result = questionApplicationService.createQuestion(command);
        return new QuestionCreatedResponse(result.questionId());
    }
}

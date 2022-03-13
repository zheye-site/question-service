package com.zheye.question.rest.request;

public record CreateQuestionRequest(
        String questionerId,
        String title,
        String detail
) {
}

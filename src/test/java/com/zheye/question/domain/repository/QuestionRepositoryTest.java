package com.zheye.question.domain.repository;

import com.zheye.question.core.JpaRepositoryTest;
import com.zheye.question.domain.model.Question;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@JpaRepositoryTest
class QuestionRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void repository_should_successfully_save_question() {
        var question = new Question("UID_00001", "A test title", "A test detail");
        var savedQuestion = questionRepository.save(question);
        assertThat(savedQuestion.getId(), is(notNullValue()));
        assertSameQuestions(savedQuestion, question);
    }

    @Test
    void repository_should_successfully_find_question_by_id() {
        var question = new Question("UID_00001", "A test title", "A test detail");
        var savedQuestion = questionRepository.saveAndFlush(question);
        entityManager.detach(savedQuestion);
        var foundQuestion = questionRepository.findById(savedQuestion.getId()).orElseThrow(AssertionFailedError::new);
        assertSameQuestions(foundQuestion, question);
    }

    private void assertSameQuestions(Question actualQuestion, Question expectedQuestion) {
        assertThat(actualQuestion.getQuestionerId(), equalTo(expectedQuestion.getQuestionerId()));
        assertThat(actualQuestion.getTitle(), equalTo(expectedQuestion.getTitle()));
        assertThat(actualQuestion.getDetail(), equalTo(expectedQuestion.getDetail()));
    }
}

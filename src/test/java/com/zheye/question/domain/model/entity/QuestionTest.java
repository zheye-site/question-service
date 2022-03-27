package com.zheye.question.domain.model.entity;

import org.junit.jupiter.api.Test;

import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdateType.CREATED;
import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdateType.DETAIL_EDITED;
import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdateType.TITLE_EDITED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class QuestionTest {

    @Test
    void should_generate_created_record_after_question_created() {
        var question = new Question("UID_00001", "A test title", "A test detail");
        var updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(1));
        var questionCreatedRecord = updatedRecords.get(0);
        assertThat(questionCreatedRecord.getUpdateType(), is(CREATED));
        assertThat(questionCreatedRecord.getCreatedTitle(), is(question.getTitle()));
        assertThat(questionCreatedRecord.getCreatedDetail(), is(question.getDetail()));
    }

    @Test
    void should_generate_edited_record_after_question_edited() {
        var originalTitle = "A test title";
        var originalDetail = "A test detail";
        var question = new Question("UID_00001", originalTitle, originalDetail);
        var editedTitle = "A new test title";
        question.editTitle("UID_00002", "for test", editedTitle);
        var editedDetail = "A new test detail";
        question.editDetail("UID_00003", "for test", editedDetail);
        var updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(3));
        var questionCreatedRecord = updatedRecords.get(0);
        assertThat(questionCreatedRecord.getUpdateType(), is(CREATED));
        assertThat(questionCreatedRecord.getCreatedTitle(), is(originalTitle));
        assertThat(questionCreatedRecord.getCreatedDetail(), is(originalDetail));
        var questionTitleEditedRecord = updatedRecords.get(1);
        assertThat(questionTitleEditedRecord.getUpdateType(), is(TITLE_EDITED));
        assertThat(questionTitleEditedRecord.getUneditedTitle(), is(originalTitle));
        assertThat(questionTitleEditedRecord.getEditedTitle(), is(editedTitle));
        var questionDetailEditedRecord = updatedRecords.get(2);
        assertThat(questionDetailEditedRecord.getUpdateType(), is(DETAIL_EDITED));
        assertThat(questionDetailEditedRecord.getUneditedDetail(), is(originalDetail));
        assertThat(questionDetailEditedRecord.getEditedDetail(), is(editedDetail));

        assertThat(question.getTitle(), is(editedTitle));
        assertThat(question.getDetail(), is(editedDetail));
    }

}

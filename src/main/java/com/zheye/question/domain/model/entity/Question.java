package com.zheye.question.domain.model.entity;

import com.zheye.question.domain.model.vo.QuestionUpdatedRecord;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String questionerId;
    private String title;
    private String detail;

    @ElementCollection
    @CollectionTable(name = "question_updated_record")
    @OrderBy("updatedAt")
    private List<QuestionUpdatedRecord> updatedRecords;

    @PersistenceConstructor
    protected Question() {
    }

    public Question(String questionerId, String title, String detail) {
        this.questionerId = questionerId;
        this.title = title;
        this.detail = detail;
        this.updatedRecords = new ArrayList<>();
        updatedRecords.add(QuestionUpdatedRecord.ofCreated(questionerId, title, detail));
    }

    public String getId() {
        return id;
    }

    public String getQuestionerId() {
        return questionerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public List<QuestionUpdatedRecord> getUpdatedRecords() {
        return Collections.unmodifiableList(updatedRecords);
    }

    public void editTitle(String editorId, String reason, String title) {
        this.updatedRecords.add(QuestionUpdatedRecord.ofTitleEdited(editorId, reason, this.title, title));
        this.title = title;
    }

    public void editDetail(String editorId, String reason, String detail) {
        this.updatedRecords.add(QuestionUpdatedRecord.ofDetailEdited(editorId, reason, this.detail, detail));
        this.detail = detail;
    }
}

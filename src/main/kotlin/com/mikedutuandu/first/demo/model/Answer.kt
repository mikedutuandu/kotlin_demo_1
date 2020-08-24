package com.mikedutuandu.first.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*
import com.mikedutuandu.first.demo.model.Question
import java.util.*

@Entity
@Table(name = "answers")
class Answer(@Id
             @GeneratedValue(generator = "answer_generator")
             @SequenceGenerator(name = "answer_generator", sequenceName = "answer_sequence", initialValue = 1000)
             var id: Long,

             @Column(columnDefinition = "text")
             var text: String? = null,

             @ManyToOne(fetch = FetchType.LAZY, optional = false)
             @JoinColumn(name = "question_id", nullable = false)
             @OnDelete(action = OnDeleteAction.CASCADE)
             @JsonIgnore
             var question: Question,
             val createdDate: Date, val lastModifiedDate: Date
) : AuditModel(createdDate, lastModifiedDate)
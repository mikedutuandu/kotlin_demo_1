package com.mikedutuandu.first.demo.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size



@Entity
@Table(name = "questions")
data class Question(
        @Id
        @GeneratedValue(generator = "question_generator")
        @SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 1000)
        var id: Long,
        var title: @NotBlank @Size(min = 3, max = 100) String? = null,

        @Column(columnDefinition = "text")
        var description: String? = null,

        @OneToMany(mappedBy = "question")
        var answers: List<Answer>,

        val createdDate: Date, val lastModifiedDate: Date
) : AuditModel(createdDate, lastModifiedDate)

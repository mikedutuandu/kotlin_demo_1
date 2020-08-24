package com.mikedutuandu.first.demo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*
import javax.persistence.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
abstract class AuditModel(
        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false)
        @CreatedDate
        var createdAt: Date,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false)
        @LastModifiedDate
        var updatedAt: Date
) : Serializable

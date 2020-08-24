package com.mikedutuandu.first.demo.repository

import com.mikedutuandu.first.demo.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface QuestionRepository : JpaRepository<Question, Long>
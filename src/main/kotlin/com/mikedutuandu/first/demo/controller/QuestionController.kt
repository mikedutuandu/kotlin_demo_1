package com.mikedutuandu.first.demo.controller

import com.mikedutuandu.first.demo.exception.ResourceNotFoundException
import com.mikedutuandu.first.demo.model.Question
import com.mikedutuandu.first.demo.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.function.Function
import java.util.function.Supplier
import javax.validation.Valid


@RestController
class QuestionController(private val questionRepository: QuestionRepository) {

    @GetMapping("/questions")
    fun getQuestions(pageable: Pageable): Page<Question> = questionRepository.findAll(pageable)

    @PostMapping("/questions")
    fun createQuestion(@Valid @RequestBody question: Question) = questionRepository.save(question)


    @PutMapping("/questions/{questionId}")
    fun updateQuestion(@PathVariable questionId: Long,
                       @Valid  @RequestBody questionRequest: Question): Question = questionRepository.findById(questionId)
                                .orElseThrow{ResourceNotFoundException("Question not found with id $questionId") }
                                .apply { this.title = questionRequest.title }
                              //.let { questionRepository.save(it)  }
                                .let(questionRepository::save)

    @DeleteMapping("/questions/{questionId}")
    fun deleteQuestion(@PathVariable questionId: Long): Unit = questionRepository.findById(questionId)
                                .orElseThrow{ResourceNotFoundException("Question not found with id $questionId") }
                                .let(questionRepository::delete)


}
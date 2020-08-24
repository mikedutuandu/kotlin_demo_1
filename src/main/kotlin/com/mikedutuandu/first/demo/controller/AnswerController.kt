package com.mikedutuandu.first.demo.controller


import com.mikedutuandu.first.demo.exception.ResourceNotFoundException
import com.mikedutuandu.first.demo.model.Answer
import com.mikedutuandu.first.demo.repository.AnswerRepository
import com.mikedutuandu.first.demo.repository.QuestionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class AnswerController(private val questionRepository: QuestionRepository,private val answerRepository: AnswerRepository) {


    @GetMapping("/questions/{questionId}/answers")
    fun getAnswersByQuestionId(@PathVariable questionId: Long): List<Answer> {
        return answerRepository.findByQuestionId(questionId)
    }

    @PostMapping("/questions/{questionId}/answers")
    fun addAnswer(@PathVariable questionId: Long,
                  @RequestBody answer: @Valid Answer): @Valid Answer {
        return questionRepository.findById(questionId)
                .orElseThrow{ResourceNotFoundException("Question not found with id $questionId") }
                .apply { answer.question = this}
                .let{ answerRepository.save(answer) }

    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    fun updateAnswer(@PathVariable questionId: Long,
                     @PathVariable answerId: Long,
                     @RequestBody answerRequest: @Valid Answer): Answer {
        if (!questionRepository.existsById(questionId)) {
            throw ResourceNotFoundException("Question not found with id $questionId")
        }
        return answerRepository.findById(answerId)
                .orElseThrow { ResourceNotFoundException("Answer not found with id $answerId") }
                .apply { text =  answerRequest.text}
                .let { answerRepository.save(it) }
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    fun deleteAnswer(@PathVariable questionId: Long,
                     @PathVariable answerId: Long):  Unit{
        if (!questionRepository.existsById(questionId)) {
            throw ResourceNotFoundException("Question not found with id $questionId")
        }
        return answerRepository.findById(answerId)
                .orElseThrow{ResourceNotFoundException("Answer not found with id $answerId") }
                .let(answerRepository::delete)
    }

}
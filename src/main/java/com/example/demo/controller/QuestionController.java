package com.example.demo.controller;

import com.example.demo.model.exam.Question;
import com.example.demo.model.exam.Quiz;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.service.addQuestion(question));
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.service.updateQuestion(question));
    }

    //get all questions of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
//        Quiz quiz =  new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questionsOfQuiz =  this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions =  quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    //duplicate
    //get all questions of any quiz
    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
        Quiz quiz =  new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz =  this.service.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

//        return ResponseEntity.ok(list);

    }

    // Get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") long quesId) {
        return this.service.getQuestion(quesId);
    }

    // Delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") long quesId) {
        this.service.deleteQuestion(quesId);
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println("Questions: " + questions);
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;

        for (Question q : questions){
            //single questions
            Question question = this.service.getQuestion(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                //correct answer
                correctAnswers++;

                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())  / questions.size();
                marksGot += marksSingle;
            }
            if( q.getGivenAnswer() != null ) {
                attempted++;
            }

        }

//        System.out.println("Marks Got: " + marksGot);
//        System.out.println("Correct Answers: " + correctAnswers);
//        System.out.println("Attempted: " + attempted);
        Map<String, Object> map = Map.of(
                "marksGot", marksGot,
                "correctAnswers", correctAnswers,
                "attempted", attempted
        );
        return ResponseEntity.ok(map);
    }




}

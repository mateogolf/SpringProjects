package com.mattr.dojooverflow.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mattr.dojooverflow.models.Answer;
import com.mattr.dojooverflow.models.Question;
import com.mattr.dojooverflow.repositories.AnswerRepo;
import com.mattr.dojooverflow.repositories.QuestionRepo;

@Service
public class QAndAService {
	private QuestionRepo qRepo;
	private AnswerRepo aRepo;
	
	public QAndAService(QuestionRepo qRepo, AnswerRepo aRepo) {
		this.qRepo = qRepo;
		this.aRepo = aRepo;
	}
	
	//Question:all
	public ArrayList<Question> allQuestions(){
		return (ArrayList<Question>)qRepo.findAll();
	}
	//View one question
	public Question questionAt(Long id) {
		return qRepo.findOne(id);
	}
	//addQuestion
	public Question addQ(Question question) {
		return qRepo.save(question);
	}
	//Answer:addAnswer
	public Answer addAnswer(Answer answer) {
		return aRepo.save(answer);
	}
	//Answer:addAnswer
//	public Answer addAnswer(Long id,Answer answer) {
//		Answer saved = aRepo.save(answer);
//		Question question = qRepo.findOne(id);
//		List<Answer> answers = question.getAnswers();
//		answers.add(saved);
//		question.setAnswers(answers);
//		qRepo.save(question);
//		return saved;
//	}
	//Answer:addAnswer
//	public Answer addAnswer(Question question,Answer answer) {
//		//add answer to database and return the answer which has the id
//		Answer saved = aRepo.save(answer);
//		//add answer to question
//		List<Answer> answers = question.getAnswers();
//		answers.add(saved);
//		question.setAnswers(answers);
//		qRepo.save(question);
//		return saved;
//	}

}

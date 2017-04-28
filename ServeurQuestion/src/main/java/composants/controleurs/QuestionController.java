package composants.controleurs;

import composants.entitees.Question;
import composants.entitees.QuestionRepository;
import composants.entitees.RabbitMQConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Question getQuestion(@PathVariable Long id) {
		return questionRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/last")
	public ResponseEntity<Question> getLastQuestion() {
		// return Collections.max(questionRepository.findAll(),
		// Comparator.comparing(c -> c.getDatecreation()));
		try {
			Question question = new RabbitMQConnector()
					.getLastQuestion(RabbitMQConnector.getConnection());
			if (question != null) {
				return ResponseEntity.status(HttpStatus.OK).body(question);
			}
			System.out.println("question est nulle");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Question> addQuestion(
			@RequestParam(value = "libelle", required = false) String libelle) {

		// for(Question item : questionRepository.findAll()) {
		// int lev = StringUtils.getLevenshteinDistance(libelle,
		// item.getLibelle());
		// double ratio = ((double) lev) / (Math.max(libelle.length(),
		// item.getLibelle().length()));
		// if (ratio <= 0.15) {
		// return ResponseEntity.status(HttpStatus.OK).body(item);
		// }
		// }

		Question q = new Question(libelle);

		// Ajout de la question dans la file rabbitMQ :
		try {
			RabbitMQConnector.sendQuestionToQueue(
					RabbitMQConnector.getConnection(), q);
			return ResponseEntity.status(HttpStatus.OK).body(
					questionRepository.saveAndFlush(q));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}

	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public
	 * void deleteQuestion(@PathVariable Long id) { questionDAO.delete(id); }
	 */

	@RequestMapping(method = RequestMethod.PUT)
	public Question updateQuestion(@RequestBody Question question) {
		return questionRepository.saveAndFlush(question);
	}

}
package composants.controleurs;

import composants.entitees.Question;
import composants.entitees.QuestionRepository;
import composants.entitees.RabbitMQConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

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

	@RequestMapping(method = RequestMethod.GET, value = "/next")
	public ResponseEntity<Question> getNextQuestion() {
		// return Collections.max(questionRepository.findAll(),
		// Comparator.comparing(c -> c.getDatecreation()));
		try {
			Question question = new RabbitMQConnector()
					.getNextQuestion(RabbitMQConnector.getConnection());
			if (question != null) {
				return ResponseEntity.status(HttpStatus.OK).body(question);
			} else
			{
				System.out.println("question est nulle");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Question> addQuestion(
			@RequestParam(value = "libelle", required = false) String libelle, HttpServletRequest request) {

		Question q = new Question(libelle);

		// Ajout de la question dans la file rabbitMQ :
		try {
			Question question = questionRepository.saveAndFlush(q);
			RabbitMQConnector.sendQuestionToQueue(RabbitMQConnector.getConnection(), question);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + question.getId());

			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(question);

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

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Question updateQuestion(@RequestBody Question question) {
		System.out.println("PUT : UPDATE QUESTION : " + question);
		return questionRepository.saveAndFlush(question);
	}

}

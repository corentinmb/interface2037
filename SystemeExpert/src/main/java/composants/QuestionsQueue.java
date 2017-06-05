package composants;

import composants.entitees.Items;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import composants.entitees.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionsQueue {

	private String URL_QUESTION_PUT;
	private  String URL_LAST_QUESTION;

	private int SLEEPING_TIME = 500;


	@Autowired
	private Items items;

	public void run() throws InterruptedException {

		URL_QUESTION_PUT = "http://serveurquestion:8081/questions";
		URL_LAST_QUESTION = "http://serveurquestion:8081/questions/last";

		while(true){
			try {
				HttpResponse<String> request = Unirest.get(URL_LAST_QUESTION).asString();

				if(request.getStatus() == HttpStatus.OK.value()){
					System.out.println("STATUS 200");
					String responseString = request.getBody();
					Question question = Question.getQuestionFromJson(responseString);
					System.out.println(question.toString());
					answer(question);
					System.out.println("question : " + responseString);
				}else if (request.getStatus() == HttpStatus.NO_CONTENT.value()) {
					System.out.println("Pas de question en attente");
					System.out.println("Le système va se mettre en veille pendant 500 millisecondes");
					Thread.sleep(SLEEPING_TIME);
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Erreur : Le système va se mettre en veille pendant 500 millisecondes");
				Thread.sleep(SLEEPING_TIME);
			}
		}
	}

	public void answer(Question question) throws UnirestException, JsonProcessingException {
		String responseQ = items.findResponse(question.getLibelle());
		System.out.println(responseQ);
		question.setReponse(responseQ);
		HttpResponse<JsonNode> response = Unirest.put(URL_QUESTION_PUT).header("Content-Type", "application/json").body(new JSONObject(question.toJson())).asJson();
		System.out.println(response.getBody());
	}

}

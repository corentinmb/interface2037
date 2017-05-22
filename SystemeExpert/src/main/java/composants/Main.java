package composants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import composants.items.Question;

public class Main {

	private static final int SLEEPING_TIME = 30000;
	
	private static final String URL_QUESTION_PUT = "http://localhost:8080/questions";
	private static final String URL_LAST_QUESTION = "http://localhost:8080/questions/last";

	public static void main(String[] args) throws InterruptedException {
		
		while(true){
			try {
				HttpResponse<String> request = Unirest.get(URL_LAST_QUESTION).asString();
				
				if(request.getStatus() == HttpStatus.OK.value()){
					System.out.println("STATUS 200");
					String responseString = request.getBody();
					Question question = Question.getQuestionFromJson(responseString);
					answer(question);
					System.out.println("question : " + responseString);
				}else if (request.getStatus() == HttpStatus.NO_CONTENT.value()) {
					System.out.println("Pas de question en attente");
					System.out.println("Le système va se mettre en veille pendant 30 secondes");
					Thread.sleep(SLEEPING_TIME);
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Erreur : Le système va se mettre en veille pendant 30 secondes");
				Thread.sleep(SLEEPING_TIME);
			}
		}
		
		
	}
	
	
	
	public static void answer(Question question) throws UnirestException, JsonProcessingException {
		question.findResponse();
		HttpResponse<JsonNode> response = Unirest.put(URL_QUESTION_PUT).header("Content-Type", "application/json").body(new JSONObject(question.toJson())).asJson();
		System.out.println(response.getBody());
	}

}

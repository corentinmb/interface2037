package composants.entitees;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;



public class Question {
	private long id;

	private String libelle;

	private String reponse;
	
	@JsonIgnore
	private Date datecreation;

	public Question() {
	}

	public Question(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public static Question getQuestionFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		//JSON from String to Object
		Question question = mapper.readValue(json, Question.class);
		return question;
	}
	
	public String toJson() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	public void findResponse() {

		this.reponse = "je suis une réponse";
		
	}

}

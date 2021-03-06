package composants.entitees;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
public class Item {
	
	private String question;
	private String response;

	public Item(String question, String response) {
		this.question = question;
		this.response = response;
	}

	public Item() {
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}

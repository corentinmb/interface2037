package composants.entitees;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="questions")
public class Question {
	
	public Question(String libelle, String reponse, Date datecreation) {
		super();
		this.libelle = libelle;
		this.reponse = reponse;
		this.datecreation = datecreation;
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

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 @NotNull
	 private String libelle;
	 
	 private String reponse;
	 
	 private Date datecreation;

}

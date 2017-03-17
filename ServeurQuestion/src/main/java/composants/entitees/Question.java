package composants.entitees;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String libelle;

	private String reponse;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datecreation", updatable = false)
	@JsonIgnore
	private Date datecreation;

	@PrePersist
	protected void onCreate() {
		datecreation = new Date();
	}

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


}

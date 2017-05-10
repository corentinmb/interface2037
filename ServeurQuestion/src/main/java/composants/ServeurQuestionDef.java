package composants;

import com.fasterxml.jackson.databind.ObjectMapper;
import composants.entitees.QuestionRepository;
import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by corentin on 27/04/2017.
 */
public class ServeurQuestionDef {
    private String URL_API = "http://localhost:8080/questions/";
    private HttpClient httpClient;
    private HttpPost request;
    private ObjectMapper mapper;
    private ArrayList<NameValuePair> postParameters;
    private HttpResponse response;

    @Autowired
    private QuestionRepository questionRepository;

    @Before
    public void init(){
        httpClient = HttpClients.createDefault();
        request = new HttpPost( URL_API );
        mapper = new ObjectMapper();
        postParameters = new ArrayList<NameValuePair>();
    }

    @Transactional
    @Quand("^l'usager pose une question au serveur$")
    public void l_usager_pose_une_question_au_serveur() throws Throwable {
        String question = "Qui est Emmanuel Macron ?";
        postParameters.add(new BasicNameValuePair("libelle", question));
        request.setEntity(new UrlEncodedFormEntity(postParameters));
        response = httpClient.execute(request);
    }

    @Alors("^le serveur indique qu'il a enregistré la question$")
    public void le_serveur_indique_qu_il_a_enregistré_la_question() throws Throwable {
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Alors("^il permet à l'usager de localiser la réponse lorsqu'elle sera disponible$")
    public void il_permet_à_l_usager_de_localiser_la_réponse_lorsqu_elle_sera_disponible() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^qu'il existe une question en attente de réponse$")
    public void qu_il_existe_une_question_en_attente_de_réponse() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Quand("^le système expert demande la prochaine question au serveur$")
    public void le_système_expert_demande_la_prochaine_question_au_serveur() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^il récupère la question en attente$")
    public void il_récupère_la_question_en_attente() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^la question suivante devient la question en attente$")
    public void la_question_suivante_devient_la_question_en_attente() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^qu'il n'existe aucune question$")
    public void qu_il_n_existe_aucune_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le serveur indique qu'il n'existe pas de question$")
    public void le_serveur_indique_qu_il_n_existe_pas_de_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le système expert se met veille avant de redemander une question$")
    public void le_système_expert_se_met_veille_avant_de_redemander_une_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^que le système expert a récupéré une question en attente$")
    public void que_le_système_expert_a_récupéré_une_question_en_attente() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^qu'il a trouvé une réponse$")
    public void qu_il_a_trouvé_une_réponse() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Quand("^il fournit la réponse au serveur$")
    public void il_fournit_la_réponse_au_serveur() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le serveur indique qu'il a enregistré la réponse à la question$")
    public void le_serveur_indique_qu_il_a_enregistré_la_réponse_à_la_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^qu'un usager a posé une question$")
    public void qu_un_usager_a_posé_une_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^aucun système expert n'a encore traité la question$")
    public void aucun_système_expert_n_a_encore_traité_la_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Quand("^l'usager demande à consulter la réponse$")
    public void l_usager_demande_à_consulter_la_réponse() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le serveur indique que la réponse n'est pas encore disponible$")
    public void le_serveur_indique_que_la_réponse_n_est_pas_encore_disponible() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^un système expert a traité la question$")
    public void un_système_expert_a_traité_la_question() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le serveur affiche la réponse du système expert$")
    public void le_serveur_affiche_la_réponse_du_système_expert() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Etantdonné("^qu'il ne peut pas fournir la réponse$")
    public void qu_il_ne_peut_pas_fournir_la_réponse() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Quand("^il notifie le serveur de son échec$")
    public void il_notifie_le_serveur_de_son_échec() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Alors("^le serveur enregistre que cette question n'a pas de réponse connue\\.$")
    public void le_serveur_enregistre_que_cette_question_n_a_pas_de_réponse_connue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

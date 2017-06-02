package composants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import composants.items.Question;
import org.json.JSONObject;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
@ApiIgnore
@SpringBootApplication
public class SystemeExpert {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SystemeExpert.class, args);
		try {
			context.getBean(QuestionsQueue.class).run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

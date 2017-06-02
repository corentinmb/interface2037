package composants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import springfox.documentation.annotations.ApiIgnore;

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

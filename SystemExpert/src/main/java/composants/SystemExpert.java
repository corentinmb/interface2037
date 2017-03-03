package composants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@SpringBootApplication
public class SystemExpert {

	public static void main(String[] args) {
		SpringApplication.run(SystemExpert.class, args);
	}

}

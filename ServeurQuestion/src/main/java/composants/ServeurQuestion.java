package composants;


import composants.entitees.Question;
import composants.entitees.QuestionRepository;
import composants.entitees.RabbitMQConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@SpringBootApplication
public class ServeurQuestion {
    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping("/")
    String home() {
        return "index";
    }

    @RequestMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Question q = questionRepository.findOne(id);
        model.addAttribute("q", q);
        return "details";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServeurQuestion.class, args);
        /*System.out.println(" CONNEXION A RABBITMQ:");
        if(RabbitMQConnector.getConnection()!= null)
            System.out.println("OK");*/
    }
}

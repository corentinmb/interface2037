package composant;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@ComponentScan("composant")
@SpringBootApplication
public class ServeurQuestion {

    /*@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServeurQuestion.class, args);
    }
}

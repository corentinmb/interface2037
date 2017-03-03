package composants.services;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlexemple")
public class ExempleWS {

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
    public  String sayHello() {
        return "hi !";
    }
}


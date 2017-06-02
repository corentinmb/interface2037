package composants.services;


import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import composants.Customer;
import composants.items.Item;
import composants.items.Items;

@RestController
@RequestMapping("/exservices")
public class ExempleWS {

	@Autowired
	private Items items;
	
	@RequestMapping(path = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
    public  String sayHello() {
        return "hi !";
    }

	@RequestMapping(path="/test", method = RequestMethod.GET)
	public List<Item> test() {
		return items.getItems();
	}
}



